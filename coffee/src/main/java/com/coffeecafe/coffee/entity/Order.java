package com.coffeecafe.coffee.entity;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private String customerName;
    private String cafeName;
    private Double totalAmount;
    private String orderType;
    private String tableNumber;
    private String paymentMethod;
    private LocalDateTime orderDate;

    @Column(name = "status", nullable = false)
    private String status = "PENDING";

    private Long cafeId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "items")
    private List<String> items;

    public Order() {}

    public Order(Long id, Long customerId, String customerName, String cafeName, Double totalAmount,
                 String orderType, String tableNumber, String paymentMethod, LocalDateTime orderDate,
                 String status, Long cafeId, List<String> items) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.cafeName = cafeName;
        this.totalAmount = totalAmount;
        this.orderType = orderType;
        this.tableNumber = tableNumber;
        this.paymentMethod = paymentMethod;
        this.orderDate = orderDate;
        this.status = status;
        this.cafeId = cafeId;
        this.items = items;
    }

    @PrePersist
    protected void onCreate() {
        this.orderDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCafeName() { return cafeName; }
    public void setCafeName(String cafeName) { this.cafeName = cafeName; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }

    public String getTableNumber() { return tableNumber; }
    public void setTableNumber(String tableNumber) { this.tableNumber = tableNumber; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getCafeId() { return cafeId; }
    public void setCafeId(Long cafeId) { this.cafeId = cafeId; }

    public List<String> getItems() { return items; }
    public void setItems(List<String> items) { this.items = items; }
}