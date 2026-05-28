package org.example.dto.request.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@RequiredArgsConstructor

public class VariableExpenseHeaderCreateDTO {
    @NotNull(message = "Cash log id is required")
    @Positive(message = "Cash log id must be positive")
    private  Integer logId;

    @NotNull(message = "Total price is required")
    @PositiveOrZero(message = "Total price cannot be negative")
    private  Integer totalPrice;

    @NotBlank(message = "Expense type is required")
    private  String expenseType;

    private  String notes;

    @NotNull(message = "Expense date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expenseDate;

}
