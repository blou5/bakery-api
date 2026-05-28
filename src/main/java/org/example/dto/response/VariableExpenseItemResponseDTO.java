package org.example.dto.response;

import org.example.entity.VariableExpenseItem;

public record VariableExpenseItemResponseDTO(
        Integer itemId,
        Integer expenseHeaderId,
        String itemName,
        Integer quantity,
        Integer unit,
        Integer unitPrice,
        Integer totalPrice
) {
    public static VariableExpenseItemResponseDTO from(VariableExpenseItem item) {
        return new VariableExpenseItemResponseDTO(
                item.getItemId(),
                item.getExpenseHeader() == null ? null : item.getExpenseHeader().getExpenseId(),
                item.getItemName(),
                item.getQuantity(),
                item.getUnit(),
                item.getUnitPrice(),
                item.getTotalPrice()
        );
    }
}
