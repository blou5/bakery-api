package org.example.dto.request.update;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class WithdrawalsUpdateDTO {
    private Integer withdrawalId;

    @NotNull(message = "Cash log id is required")
    @Positive(message = "Cash log id must be positive")
    private Integer logId;

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

