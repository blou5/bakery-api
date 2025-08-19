package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.mapper.WithdrawalsMapper;
import org.example.dto.request.create.WithdrawalsCreateDto;
import org.example.dto.request.update.WithdrawalsUpdateDTO;
import org.example.entity.DailyCashLog;
import org.example.entity.Withdrawals;
import org.example.repositories.DailyCashLogRepository;
import org.example.repositories.WithdrawalsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WithdrawalsService {

    private final WithdrawalsRepository withdrawalsRepository;
    private final WithdrawalsMapper withdrawalsMapper;
    private final DailyCashLogRepository dailyCashLogRepository;

    public List<Withdrawals> findAll() {
        return withdrawalsRepository.findAll();
    }

    public Optional<Withdrawals> findById(Integer id) {
        return withdrawalsRepository.findById(id);
    }

    @Transactional
    public Withdrawals save(WithdrawalsCreateDto dto) {
        Withdrawals entity = withdrawalsMapper.toEntity(dto);
        return withdrawalsRepository.save(entity);
    }

    @Transactional
    public void deleteById(Integer id) {
        withdrawalsRepository.deleteById(id);
    }


    public List<Withdrawals> getWithdrawFromDate(LocalDate filterDate){
        return this.withdrawalsRepository.findByDate(filterDate);
    }

    @Transactional
    public Withdrawals update(WithdrawalsUpdateDTO dto) {

        Withdrawals entity = withdrawalsRepository.findById(dto.getWithdrawalId())
                .orElseThrow(() -> new IllegalArgumentException("Withdrawal not found with ID: " + dto.getWithdrawalId()));

        DailyCashLog log = dailyCashLogRepository.findById(dto.getLogId())
                .orElseThrow(() -> new IllegalArgumentException("Cash log not found with ID: " + dto.getLogId()));

        entity.setLog(log);
        entity.setAmount(dto.getAmount());
        entity.setReason(dto.getReason());
        entity.setDate(dto.getDate());
        entity.setPerson(dto.getPerson());
        entity.setNotes(dto.getNotes());

        return withdrawalsRepository.save(entity);
    }
}