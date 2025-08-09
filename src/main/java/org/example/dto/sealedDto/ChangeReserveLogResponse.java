package org.example.dto.sealedDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@SuperBuilder
public final class ChangeReserveLogResponse implements ChangeReserveActionResponse {
    private Integer reserveLogId;
    private Integer denomination;
    private Integer quantity;
    private Integer amount;
    private String reserveType;
    private String status;
    private LocalDateTime createdAt;
}
