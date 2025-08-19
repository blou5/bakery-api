package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "withdrawals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Withdrawals {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "withdrawals_seq")
    @SequenceGenerator(name = "withdrawals_seq", sequenceName = "withdrawals_seq", allocationSize = 1)

    private Integer withdrawalId;

    @ManyToOne
    @JoinColumn(name = "log_id", nullable = false)
    private DailyCashLog log;

    private Integer amount;
    private String reason;

    @Column(nullable = false)
    private LocalDate date;

    private String person;
    private String notes;
}