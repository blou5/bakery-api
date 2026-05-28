package org.example.dto.request.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class UnsoldUpdateProductDto {
    private Integer unsoldId;

    @NotNull(message = "Quantity unsold is required")
    @PositiveOrZero(message = "Quantity unsold cannot be negative")
    private Integer quantityUnsold;
}
