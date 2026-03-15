package com.coffeecafe.coffee.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "cafe_tables")
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tableNumber;
    private Integer capacity;
    private Double price;
<<<<<<< HEAD
    private String status = "Available"; // Available, Occupied, Booked
=======
>>>>>>> 2b8e9abdb83ddba996deae458df54f7e2258da81

    @ElementCollection
    @CollectionTable(name = "table_images", joinColumns = @JoinColumn(name = "table_id"))
    @Column(name = "image_data", columnDefinition = "LONGTEXT")
    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "cafe_id", insertable = true, updatable = true)
    private Cafe cafe;
}