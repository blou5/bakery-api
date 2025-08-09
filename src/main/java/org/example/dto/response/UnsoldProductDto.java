package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class UnsoldProductDto {

    private Integer unsoldId;
    private Integer logId;
    private Integer productId;
    private String productName;
    private Integer quantityUnsold;
    private Integer unitCost;
    private LocalDate logDate;

}
