package org.example.dto.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class ProductionCreateDTO {

    private int logId;
    private int productId;
    private Integer quantityProduced;
    private LocalDate productionDate;
}
