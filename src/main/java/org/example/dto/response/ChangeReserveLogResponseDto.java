package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class ChangeReserveLogResponseDto {

    private Integer denomination;
    private Integer quantity;
    private Integer amount;

}
