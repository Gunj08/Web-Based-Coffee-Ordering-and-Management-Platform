package com.coffeecafe.coffee.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CafeTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableNumber;
    private int capacity;
    private double price;
    private String status; // Available, Booked

    @ElementCollection
    @CollectionTable(name = "table_images", joinColumns = @JoinColumn(name = "table_id"))
    @Column(name = "image_data", columnDefinition = "LONGTEXT")
    private List<String> images = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTableNumber() { return tableNumber; }
    public void setTableNumber(String tableNumber) { this.tableNumber = tableNumber; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    public Cafe getCafe() { return cafe; }
    public void setCafe(Cafe cafe) { this.cafe = cafe; }
}