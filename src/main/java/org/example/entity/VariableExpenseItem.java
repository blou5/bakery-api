package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "variable_expense_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class VariableExpenseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "variable_expense_items_seq")
    @SequenceGenerator(name = "variable_expense_items_seq", sequenceName = "variable_expense_items_seq", allocationSize = 1)
    private Integer itemId;

    @ManyToOne
    @JoinColumn(name = "expense_id", nullable = false)
    @JsonBackReference
    private VariableExpenseHeader expenseHeader;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer unit;

    @Column(nullable = false)
    private Integer unitPrice;

    @Column(nullable = false)
    private Integer totalPrice;
}
