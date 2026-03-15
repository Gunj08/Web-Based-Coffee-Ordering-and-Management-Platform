package com.coffeecafe.coffee.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Step 1: Personal & Account
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String gender;
    private String role;
    private String email;
    private String password;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    private String status;

    // Step 2: Address Details
    private String plotNo;
    private String area;
    private String city;
    private String pincode;

    // Step 3: Academic Details
    private String institution;
    private String degree;
    private String passingYear;
    private String govtProofPath;

    // Step 4: Work Experience Details
    private String jobTitle;
    private String companyName;
    private String employmentType;
    private String totalYears;
    private String startDate;
    private String endDate;
    private boolean currentlyWorking;

    @Column(columnDefinition = "TEXT")
    private String responsibilities;

    @Column(columnDefinition = "TEXT")
    private String achievements;

    // The Cafe Connection
    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;
}