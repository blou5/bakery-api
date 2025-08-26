package org.example.dto.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.entity.DailyCashLog;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class WithdrawalsCreateDto {
    private Integer log;

    private Integer amount;
    private String reason;

    private LocalDateTime date;

    private String person;
    private String notes;
}
