package org.example.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.entity.DailyCashLog;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class VariableExpenseHeaderUpdateDTO {

    private Integer expenseId;

    private DailyCashLog log;

    private Integer totalPrice;

    private String expenseType;

    private String notes;

    private LocalDate expenseDate;
}
