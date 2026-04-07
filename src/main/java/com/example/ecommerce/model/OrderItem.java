package com.example.ecommerce.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;
    private BigDecimal priceAtTimeOfPurchase;

    public OrderItem() {}

    public OrderItem(Product product, int quantity, BigDecimal priceAtTimeOfPurchase) {
        this.product = product;
        this.quantity = quantity;
        this.priceAtTimeOfPurchase = priceAtTimeOfPurchase;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public BigDecimal getPriceAtTimeOfPurchase() { return priceAtTimeOfPurchase; }
    public void setPriceAtTimeOfPurchase(BigDecimal priceAtTimeOfPurchase) { this.priceAtTimeOfPurchase = priceAtTimeOfPurchase; }
}
