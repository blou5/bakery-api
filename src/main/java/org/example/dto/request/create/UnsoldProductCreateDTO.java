package org.example.dto.request.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class UnsoldProductCreateDTO {
    @NotNull(message = "Cash log id is required")
    @Positive(message = "Cash log id must be positive")
    private Integer logId;

    @NotNull(message = "Product id is required")
    @Positive(message = "Product id must be positive")
    private Integer productId;

    @NotNull(message = "Quantity unsold is required")
    @PositiveOrZero(message = "Quantity unsold cannot be negative")
    private Integer quantityUnsold;

    @NotNull(message = "Unit cost is required")
    @PositiveOrZero(message = "Unit cost cannot be negative")
    private Integer unitCost;
}
