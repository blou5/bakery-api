package org.example.dto.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class UnsoldProductCreateDTO {
    private Integer logId;
    private Integer productId;
    private Integer quantityUnsold;
    private Integer unitCost;
}
