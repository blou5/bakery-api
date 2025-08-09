package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "change_reserve_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeReserveLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "change_reserve_log_seq")
    @SequenceGenerator(name = "change_reserve_log_seq", sequenceName = "change_reserve_log_seq", allocationSize = 1)
    private Integer reserveLogId;

    @Column(name = "denomination", nullable = false, precision = 10, scale = 2)
    private Integer denomination;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private Integer amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "reserve_type", nullable = false, length = 50)
    private ReserveType reserveType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private Status status;


    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "status_changed_at")
    private LocalDateTime statusChangedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public enum ReserveType {
        ADDITION,
        SUBTRACTION
    }

    public enum Status {
        PENDING,
        LIQUIDIFIED
    }

}