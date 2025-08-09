package org.example.dto.sealedDto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class LiquidifyResponseDTO implements ChangeReserveActionResponse {
    private Integer liquidifiedAmount;
    private Integer remainingPendingAmount;
}
