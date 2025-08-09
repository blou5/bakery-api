package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "daily_cash_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DailyCashLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "daily_cash_log_seq")
    @SequenceGenerator(name = "daily_cash_log_seq", sequenceName = "daily_cash_log_seq", allocationSize = 1)
    private Integer logId;

    @Column(nullable = false)
    private LocalDate logDate;
    @Column(name = "opening_cash")
    private Integer openingCash;

    @Column(name = "cash_withdrawn")
    private Integer cashWithdrawn;
    @Column(name = "closing_cash")
    private Integer closingCash;

    private String notes;
    private String weather;

    private Boolean holiday;
    private String holidayType;
    @Column(name = "expected_cash")
    private Integer expectedCash;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Status status;

    public enum Status {
        EQUAL,
        LESS,
        MORE,
        NOT_COMPLETED
    }
}
