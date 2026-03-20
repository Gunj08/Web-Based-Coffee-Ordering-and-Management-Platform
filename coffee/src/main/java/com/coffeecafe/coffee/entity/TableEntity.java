package com.coffeecafe.coffee.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cafe_tables")
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableNumber;
    private Integer capacity;
    private Double price;
    private String status = "Available"; // Available, Occupied, Booked
    private String description;
    private String amenities;

    @ElementCollection
    @CollectionTable(name = "table_images", joinColumns = @JoinColumn(name = "table_id"))
    @Column(name = "image_data", columnDefinition = "LONGTEXT")
    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "cafe_id", insertable = true, updatable = true)
    private Cafe cafe;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTableNumber() { return tableNumber; }
    public void setTableNumber(String tableNumber) { this.tableNumber = tableNumber; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    public Cafe getCafe() { return cafe; }
    public void setCafe(Cafe cafe) { this.cafe = cafe; }
}