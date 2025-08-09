package org.example.dto.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class DailyCashLogCreateDTO {
    private LocalDate logDate;
    private Integer openingCash;
    private Integer cashWithdrawn;
    private Integer closingCash;

    private String notes;
    private String weather;

    private Boolean holiday;
    private String holidayType;


}
