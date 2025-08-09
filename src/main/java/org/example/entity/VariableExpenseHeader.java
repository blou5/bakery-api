package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "variable_expense_headers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class VariableExpenseHeader {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variable_expense_headers_seq")
    @SequenceGenerator(name = "variable_expense_headers_seq", sequenceName = "variable_expense_headers_seq", allocationSize = 1)
    private Integer expenseId;

    @ManyToOne
    @JoinColumn(name = "log_id", nullable = false)
    private DailyCashLog log;

    @Column(nullable = false)
    private Integer totalPrice;

    @Column(nullable = false)
    private String expenseType;

    private String notes;

    @Column(name = "expense_date")
    private LocalDate expenseDate;

    @OneToMany(mappedBy = "expenseHeader", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<VariableExpenseItem> expenseHeaders = new ArrayList<>();
}
