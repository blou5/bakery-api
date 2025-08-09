package org.example.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class VariableExpenseItemUpdateDTO {

    private Integer itemId;

    private String itemName;

    private Integer quantity;

    private Integer unit;

    private Integer unitPrice;

    private Integer totalPrice;
    private Integer expenseHeader;
}
