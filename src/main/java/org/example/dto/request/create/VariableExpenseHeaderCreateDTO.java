package org.example.dto.request.create;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@RequiredArgsConstructor

public class VariableExpenseHeaderCreateDTO {
    private  Integer logId;
    private  Integer totalPrice;
    private  String expenseType;
    private  String notes;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate expenseDate;

}
