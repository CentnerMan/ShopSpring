package ru.vlsv.ShopSpring.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 29.10.2019
 * @link https://github.com/Centnerman
 */

@Entity
@Table(name = "purchases")
@Data
@NoArgsConstructor
public class Purchase implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    @Override
    public String toString() {
        return "Purchase{" +
                "customer=" + customer +
                ", product=" + product +
                ", purchasePrice=" + purchasePrice +
                '}';
    }
}
