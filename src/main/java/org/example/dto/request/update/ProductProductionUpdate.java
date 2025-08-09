package org.example.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class ProductProductionUpdate {
    private Integer productionId;
    private Integer logId;
    private Integer quantityProduced;
    private Integer productId;
}
