package org.example.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class ProductCreateDTO {

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotNull(message = "Selling price is required")
    @PositiveOrZero(message = "Selling price cannot be negative")
    private Integer sellingPrice;

}
