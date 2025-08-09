package org.example.dto.sealedDto;

public sealed interface ChangeReserveActionResponse permits ChangeReserveLogResponse , LiquidifyResponseDTO {
}