package org.example.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class LiquidifyRequestDTO {
    @NotNull(message = "Denomination is required")
    @Positive(message = "Denomination must be positive")
    private Integer denomination;

    @NotNull(message = "Target amount is required")
    @Positive(message = "Target amount must be positive")
    private Integer targetAmount;
}
