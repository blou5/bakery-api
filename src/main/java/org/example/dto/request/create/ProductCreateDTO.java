package org.example.dto.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class ProductCreateDTO {

    private String productName;
    private Integer sellingPrice;

}
