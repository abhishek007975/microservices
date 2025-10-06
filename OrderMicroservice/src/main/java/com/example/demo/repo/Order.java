package com.example.demo.repo;
 
import jakarta.persistence.*;
 
@Entity
@Table(name="orders")
public class Order {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column
    private Long productId;
 
    @Column
    private int quantity;
 
    @Column
    private String status;
 
    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
 
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
 
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
 