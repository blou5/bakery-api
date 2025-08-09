package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.mapper.DailyCashLogMapper;
import org.example.dto.request.create.DailyCashLogCreateDTO;
import org.example.entity.DailyCashLog;
import org.example.entity.ProductProduction;
import org.example.entity.UnsoldProduct;
import org.example.entity.Withdrawals;
import org.example.repositories.DailyCashLogRepository;
import org.example.repositories.ProductProductionRepository;
import org.example.repositories.UnsoldProductRepository;
import org.example.repositories.WithdrawalsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DailyCashLogService {

    private final DailyCashLogRepository dailyCashLogRepository;
    private final DailyCashLogMapper dailyCashLogMapper;
    private final ProductProductionRepository productProductionRepository;
    private final UnsoldProductRepository unsoldProductRepository;
    private final WithdrawalsRepository withdrawalsRepository;

    public List<DailyCashLog> findAll() {
        return dailyCashLogRepository.findAll();
    }

    public Optional<DailyCashLog> findById(Integer id) {
        return dailyCashLogRepository.findById(id);
    }

    @Transactional
    public DailyCashLog create(DailyCashLogCreateDTO dto) {

        List<DailyCashLog> existing = dailyCashLogRepository.findByLogDate(dto.getLogDate());

        if (!existing.isEmpty()) {
            throw new IllegalStateException("A DailyCashLog already exists for date: " + dto.getLogDate());
        }
        DailyCashLog entity = dailyCashLogMapper.toEntity(dto);
        entity.setStatus(DailyCashLog.Status.NOT_COMPLETED);
        return dailyCashLogRepository.save(entity);
    }

    @Transactional
    public void deleteById(Integer id) {
        dailyCashLogRepository.deleteById(id);
    }


    public Optional<DailyCashLog> getLastCashLog (LocalDate localDate){
        List<DailyCashLog> byLogDate = dailyCashLogRepository.findByLogDate(localDate);
        if (byLogDate.size() > 1){
            throw new RuntimeException("There are more then a Daily Cash log provided");
        }

        return Optional.of(byLogDate.getFirst());

    }
    @Transactional
    public DailyCashLog update(Integer logId, DailyCashLog dto) {
        DailyCashLog existing = dailyCashLogRepository.findById(logId)
                .orElseThrow(() -> new EntityNotFoundException("DailyCashLog not found with ID " + logId));
        System.out.println(dto.toString());
        // Apply updates manually or via mapper
        existing.setLogDate(dto.getLogDate());
        existing.setOpeningCash(dto.getOpeningCash());
        existing.setCashWithdrawn(dto.getCashWithdrawn());
        existing.setClosingCash(dto.getClosingCash());
        existing.setExpectedCash(dto.getExpectedCash());
        existing.setNotes(dto.getNotes());
        existing.setWeather(dto.getWeather());
        existing.setHoliday(dto.getHoliday());
        existing.setHolidayType(dto.getHolidayType());

        // Recalculate status
        if (dto.getExpectedCash() != null && dto.getClosingCash() != null) {
            int expected = dto.getExpectedCash();
            int closing = dto.getClosingCash();
            int withdrawn= dto.getCashWithdrawn();
            int total = closing + withdrawn;
            if (expected == total) {
                existing.setStatus(DailyCashLog.Status.EQUAL);
            } else if (expected > total) {
                existing.setStatus(DailyCashLog.Status.LESS);
            } else {
                existing.setStatus(DailyCashLog.Status.MORE);
            }
        } else {
            existing.setStatus(DailyCashLog.Status.NOT_COMPLETED);
        }
        DailyCashLog save = dailyCashLogRepository.save(existing);
        System.out.println(save.toString());
        return save;
    }


    public Integer calculateExpectedCash(Integer logId) {
        DailyCashLog log = dailyCashLogRepository.findById(logId)
                .orElseThrow(() -> new EntityNotFoundException("Log not found"));

        // 1. Get total production value
        List<ProductProduction> productions = productProductionRepository.findByLog_LogId(logId);
        int totalProducedValue = productions.stream()
                .mapToInt(p -> p.getQuantityProduced() * p.getProduct().getSellingPrice())
                .sum();

        // 2. Get total unsold value
        List<UnsoldProduct> unsold = unsoldProductRepository.findByLog_LogId(logId);
        int totalUnsoldValue = unsold.stream()
                .mapToInt(u -> u.getQuantityUnsold() * u.getProduct().getSellingPrice())
                .sum();

        // 3. Get total withdrawals
        List<Withdrawals> withdrawals = withdrawalsRepository.findByLog_LogId(logId);
        int totalWithdrawals = withdrawals.stream()
                .mapToInt(Withdrawals::getAmount)
                .sum();

        return log.getOpeningCash() + totalProducedValue - totalUnsoldValue - totalWithdrawals;
    }

}