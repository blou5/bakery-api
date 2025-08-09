package org.example.dto.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class VariableExpenseItemCreateDTO {
    private Integer expenseHeader;
    private String itemName;
    private Integer quantity;
    private Integer unit;
    private Integer unitPrice;
    private Integer totalPrice;
}
