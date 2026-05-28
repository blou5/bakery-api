package org.example.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.entity.DailyCashLog;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class VariableExpenseHeaderUpdateDTO {

    @NotNull(message = "Expense id is required")
    @Positive(message = "Expense id must be positive")
    private Integer expenseId;

    private DailyCashLog log;

    @NotNull(message = "Total price is required")
    @PositiveOrZero(message = "Total price cannot be negative")
    private Integer totalPrice;

    @NotBlank(message = "Expense type is required")
    private String expenseType;

    private String notes;

    @NotNull(message = "Expense date is required")
    private LocalDate expenseDate;
}
