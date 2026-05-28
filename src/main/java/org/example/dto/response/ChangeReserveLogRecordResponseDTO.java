package org.example.dto.response;

import org.example.entity.ChangeReserveLog;

import java.time.LocalDateTime;

public record ChangeReserveLogRecordResponseDTO(
        Integer reserveLogId,
        Integer denomination,
        Integer quantity,
        Integer amount,
        String reserveType,
        String status,
        LocalDateTime createdAt,
        LocalDateTime statusChangedAt
) {
    public static ChangeReserveLogRecordResponseDTO from(ChangeReserveLog log) {
        return new ChangeReserveLogRecordResponseDTO(
                log.getReserveLogId(),
                log.getDenomination(),
                log.getQuantity(),
                log.getAmount(),
                log.getReserveType() == null ? null : log.getReserveType().name(),
                log.getStatus() == null ? null : log.getStatus().name(),
                log.getCreatedAt(),
                log.getStatusChangedAt()
        );
    }
}
