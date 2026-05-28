package org.example.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class VariableExpenseItemCreateDTO {
    @NotNull(message = "Expense header id is required")
    @Positive(message = "Expense header id must be positive")
    private Integer expenseHeader;

    @NotBlank(message = "Item name is required")
    private String itemName;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @NotNull(message = "Unit is required")
    @Positive(message = "Unit must be positive")
    private Integer unit;

    @NotNull(message = "Unit price is required")
    @PositiveOrZero(message = "Unit price cannot be negative")
    private Integer unitPrice;

    @NotNull(message = "Total price is required")
    @PositiveOrZero(message = "Total price cannot be negative")
    private Integer totalPrice;
}
