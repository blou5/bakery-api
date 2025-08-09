package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "unsold_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UnsoldProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unsold_products_seq")
    @SequenceGenerator(name = "unsold_products_seq", sequenceName = "unsold_products_seq", allocationSize = 1)
    private Integer unsoldId;

    @ManyToOne
    @JoinColumn(name = "log_id", nullable = false)
    private DailyCashLog log;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantityUnsold;

    @Column(nullable = false)
    private Integer unitCost;
}
