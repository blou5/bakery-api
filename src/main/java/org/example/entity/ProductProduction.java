package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "product_production")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_production_seq")
    @SequenceGenerator(name = "product_production_seq", sequenceName = "product_production_seq", allocationSize = 1)
    private Integer productionId;

    @ManyToOne
    @JoinColumn(name = "log_id", nullable = false)
    private DailyCashLog log;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantityProduced;
}
