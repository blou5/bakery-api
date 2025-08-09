package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.mapper.ChangeReserveLogMapper;
import org.example.dto.request.LiquidifyRequestDTO;
import org.example.dto.request.create.ChangeReserveLogRequestDTO;
import org.example.dto.response.ChangeReserveLogResponseDto;
import org.example.dto.sealedDto.ChangeReserveActionResponse;
import org.example.dto.sealedDto.ChangeReserveLogResponse;
import org.example.dto.sealedDto.LiquidifyResponseDTO;
import org.example.entity.ChangeReserveLog;
import org.example.repositories.ChangeReserveLogRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChangeReserveLogService {

    private final ChangeReserveLogRepository changeReserveLogRepository;
    private final ChangeReserveLogMapper changeReserveLogMapper;

    public Page<ChangeReserveLog> findAll(Pageable pageable){
        return changeReserveLogRepository.findAll(pageable);
    }

    public Optional<ChangeReserveLog> findById(Integer id) {
        return changeReserveLogRepository.findById(id);
    }

    @Transactional
    public ChangeReserveActionResponse save(ChangeReserveLogRequestDTO entity) {
        return handleChangeReserveAction(entity);
    }

    public ChangeReserveActionResponse handleChangeReserveAction(ChangeReserveLogRequestDTO dto) {
        if (dto.getReserveType() == ChangeReserveLog.ReserveType.ADDITION) {
            ChangeReserveLog entity = changeReserveLogMapper.toEntity(dto);

            ChangeReserveLog save = changeReserveLogRepository.save(entity);
            return ChangeReserveLogResponse.builder()
                    .quantity(save.getQuantity())
                    .denomination(save.getDenomination())
                    .reserveType(String.valueOf(save.getReserveType()))
                    .amount(save.getAmount())
                    .reserveLogId(save.getReserveLogId())
                    .status(String.valueOf(save.getStatus()))
                    .createdAt(LocalDateTime.now())
                    .build();
        } else if (dto.getReserveType() == ChangeReserveLog.ReserveType.SUBTRACTION) {

            LiquidifyRequestDTO request = new LiquidifyRequestDTO();
            request.setDenomination(dto.getDenomination());
            request.setTargetAmount(dto.getDenomination() * dto.getQuantity());

           return validateAndLiquidify(request); // apply the logic to existing PENDING entries
        }
        throw new IllegalArgumentException("Unsupported reserve type: " + dto.getReserveType());
    }

    @Transactional
    public void deleteById(Integer id) {
        changeReserveLogRepository.deleteById(id);
    }


    public LiquidifyResponseDTO validateAndLiquidify(LiquidifyRequestDTO dto) {
        Integer availablePending = getPendingAmount(dto.getDenomination());
        Integer target = dto.getTargetAmount();

        if (availablePending == 0) {
            throw new IllegalStateException("No available PENDING reserve to liquidify.");
        }

        if (availablePending < target) {
            // You can throw, return warning, or allow partial
            throw new IllegalStateException("Not enough PENDING amount. Requested: " + target + ", Available: " + availablePending);

            // OR: If you want to allow partial liquidification, just pass through
            // and let liquidify() stop at the available limit.
        }

        return liquidify(dto); // safe to proceed
    }

    @Transactional
    public LiquidifyResponseDTO liquidify(LiquidifyRequestDTO dto) {
        Integer denomination = dto.getDenomination();
        Integer targetAmount = dto.getTargetAmount();

        List<ChangeReserveLog> records = changeReserveLogRepository.findByStatusAndDenominationOrdered(
                ChangeReserveLog.Status.PENDING, denomination);

        int liquidifiedSum = 0;
        boolean modified = false;

        for (ChangeReserveLog log : records) {
            int logAmount = log.getAmount();

            if (liquidifiedSum + logAmount <= targetAmount) {
                log.setStatus(ChangeReserveLog.Status.LIQUIDIFIED);
                log.setStatusChangedAt(LocalDateTime.now());
                liquidifiedSum += logAmount;
                modified = true;
            } else if (liquidifiedSum < targetAmount) {
                int amountToLiquidify = targetAmount - liquidifiedSum;
                int totalQuantity = log.getQuantity();
                int liquidifyQuantity = amountToLiquidify / log.getDenomination();

                if (liquidifyQuantity == 0) break; // Not enough to liquidify even smallest coin

                log.setQuantity(totalQuantity - liquidifyQuantity);
                log.setAmount(log.getAmount() - (liquidifyQuantity * log.getDenomination()));

                ChangeReserveLog liquidifiedPart = new ChangeReserveLog();

                liquidifiedPart.setDenomination(log.getDenomination());
                liquidifiedPart.setQuantity(liquidifyQuantity);
                liquidifiedPart.setAmount(amountToLiquidify);
                liquidifiedPart.setReserveType(log.getReserveType());
                liquidifiedPart.setStatus(ChangeReserveLog.Status.LIQUIDIFIED);
                liquidifiedPart.setStatusChangedAt(LocalDateTime.now());
                liquidifiedPart.setCreatedAt(LocalDateTime.now());

                changeReserveLogRepository.save(liquidifiedPart);
                liquidifiedSum += amountToLiquidify;
                modified = true;
                break;
            } else {
                break;
            }
        }

        if (modified) {
            changeReserveLogRepository.saveAll(records);
        }

        Integer remainingPending = getPendingAmount(denomination);

        return new LiquidifyResponseDTO(liquidifiedSum, remainingPending);
    }


    public Integer getPendingAmount(Integer denomination) {
        return changeReserveLogRepository.findByStatusAndDenominationOrdered(ChangeReserveLog.Status.PENDING, denomination)
                .stream()
                .map(ChangeReserveLog::getAmount)
                .reduce(0, Integer::sum);
    }

    public List<ChangeReserveLogResponseDto> getPendingSummaryByDenomination() {
        return changeReserveLogRepository.findByStatus(ChangeReserveLog.Status.PENDING).stream()
                .collect(Collectors.groupingBy(
                        ChangeReserveLog::getDenomination
                ))
                .entrySet().stream()
                .map(entry -> {
                    Integer denomination = entry.getKey();
                    List<ChangeReserveLog> logs = entry.getValue();

                    int totalQuantity = logs.stream().mapToInt(ChangeReserveLog::getQuantity).sum();
                    int totalAmount = logs.stream().mapToInt(ChangeReserveLog::getAmount).sum();

                    return new ChangeReserveLogResponseDto(denomination, totalQuantity, totalAmount);
                })
                .toList();
    }
}

