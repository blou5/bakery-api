package org.example.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class WithdrawalsCreateDto {
    @NotNull(message = "Cash log id is required")
    private Integer log;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Integer amount;

    private String reason;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Person is required")
    private String person;

    private String notes;
}
