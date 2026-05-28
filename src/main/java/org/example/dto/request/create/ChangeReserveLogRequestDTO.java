package org.example.dto.request.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.example.entity.ChangeReserveLog.ReserveType;

@Data
public class ChangeReserveLogRequestDTO {
    @NotNull(message = "Denomination is required")
    @Positive(message = "Denomination must be positive")
    private Integer denomination;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @NotNull(message = "Reserve type is required")
    private ReserveType reserveType;
}
