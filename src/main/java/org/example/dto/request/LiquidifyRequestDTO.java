package org.example.dto.request;

import lombok.Data;

@Data
public class LiquidifyRequestDTO {
    private Integer denomination;
    private Integer targetAmount;
}
