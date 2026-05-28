package org.example.dto.response;

import org.example.entity.Withdrawals;

import java.time.LocalDate;

public record WithdrawalsResponseDTO(
        Integer withdrawalId,
        Integer logId,
        Integer amount,
        String reason,
        LocalDate date,
        String person,
        String notes
) {
    public static WithdrawalsResponseDTO from(Withdrawals withdrawals) {
        return new WithdrawalsResponseDTO(
                withdrawals.getWithdrawalId(),
                withdrawals.getLog() == null ? null : withdrawals.getLog().getLogId(),
                withdrawals.getAmount(),
                withdrawals.getReason(),
                withdrawals.getDate(),
                withdrawals.getPerson(),
                withdrawals.getNotes()
        );
    }
}
