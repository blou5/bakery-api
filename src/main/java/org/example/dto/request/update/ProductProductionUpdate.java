package org.example.dto.request.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class ProductProductionUpdate {
    private Integer productionId;

    @NotNull(message = "Cash log id is required")
    @Positive(message = "Cash log id must be positive")
    private Integer logId;

    @NotNull(message = "Quantity produced is required")
    @Positive(message = "Quantity produced must be positive")
    private Integer quantityProduced;

    @NotNull(message = "Product id is required")
    @Positive(message = "Product id must be positive")
    private Integer productId;
}
