package org.example.dto.request.update;

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
public class VariableExpenseItemUpdateDTO {

    @NotNull(message = "Item id is required")
    @Positive(message = "Item id must be positive")
    private Integer itemId;

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

    @NotNull(message = "Expense header id is required")
    @Positive(message = "Expense header id must be positive")
    private Integer expenseHeader;
}
