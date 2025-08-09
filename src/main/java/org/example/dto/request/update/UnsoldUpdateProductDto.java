package org.example.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class UnsoldUpdateProductDto {
    private Integer unsoldId;
    private Integer quantityUnsold;
}
