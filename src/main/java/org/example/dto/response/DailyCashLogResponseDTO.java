package org.example.dto.response;

import org.example.entity.DailyCashLog;

import java.time.LocalDate;

public record DailyCashLogResponseDTO(
        Integer logId,
        LocalDate logDate,
        Integer openingCash,
        Integer cashWithdrawn,
        Integer closingCash,
        Integer expectedCash,
        String status,
        String notes,
        String weather,
        Boolean holiday,
        String holidayType
) {
    public static DailyCashLogResponseDTO from(DailyCashLog log) {
        return new DailyCashLogResponseDTO(
                log.getLogId(),
                log.getLogDate(),
                log.getOpeningCash(),
                log.getCashWithdrawn(),
                log.getClosingCash(),
                log.getExpectedCash(),
                log.getStatus() == null ? null : log.getStatus().name(),
                log.getNotes(),
                log.getWeather(),
                log.getHoliday(),
                log.getHolidayType()
        );
    }
}
