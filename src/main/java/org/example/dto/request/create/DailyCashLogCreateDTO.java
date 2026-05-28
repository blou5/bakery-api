package org.example.dto.request.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class DailyCashLogCreateDTO {
    @NotNull(message = "Log date is required")
    private LocalDate logDate;

    @NotNull(message = "Opening cash is required")
    @PositiveOrZero(message = "Opening cash cannot be negative")
    private Integer openingCash;

    @PositiveOrZero(message = "Cash withdrawn cannot be negative")
    private Integer cashWithdrawn;

    @PositiveOrZero(message = "Closing cash cannot be negative")
    private Integer closingCash;

    private String notes;
    private String weather;

    private Boolean holiday;
    private String holidayType;


}
