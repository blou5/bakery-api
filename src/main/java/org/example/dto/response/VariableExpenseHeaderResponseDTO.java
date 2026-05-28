package org.example.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class VariableExpenseHeaderResponseDTO {

    private Integer expenseId;
    private Integer totalPrice;

    private String expenseType;

    private String notes;

    private LocalDate expenseDate;

    private List<VariableExpenseItemResponseDTO> expenseHeaders;

}
