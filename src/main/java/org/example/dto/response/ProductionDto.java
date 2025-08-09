package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class ProductionDto {
    private Integer productionId;
    private String productName;
    private LocalDate productionDate;
    private Integer quantityProduced;
    private Integer logId;
    private Integer productId;
}
