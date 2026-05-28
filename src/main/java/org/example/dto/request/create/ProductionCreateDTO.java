package org.example.dto.request.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class ProductionCreateDTO {

    @Positive(message = "Cash log id must be positive")
    private int logId;

    @Positive(message = "Product id must be positive")
    private int productId;

    @NotNull(message = "Quantity produced is required")
    @Positive(message = "Quantity produced must be positive")
    private Integer quantityProduced;

    private LocalDate productionDate;
}
