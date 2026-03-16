package com.coffeecafe.coffee.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

public class RegisterRequest {

    // Step 1: Personal & Account
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotNull(message = "Date of birth is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Role is required")
    private String role;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    // Step 2: Address Details
    private String plotNo;
    private String area;    // Matches 'area' in your form
    private String street;  // Kept for backward compatibility
    private String city;
    private String pincode;

    // Step 3: Academic Details
    private String institution;
    private String degree;
    private String year;

    // Step 4: Work Experience Details
    private String jobTitle;
    private String companyName;
    private String employmentType;
    private String totalYears;
    private String startDate;
    private String endDate;
    private boolean currentlyWorking;
    private String responsibilities;
    private String achievements;

    // Summary field (optional)
    private String workExperience;

    private Long cafeId;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getPlotNo() { return plotNo; }
    public void setPlotNo(String plotNo) { this.plotNo = plotNo; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getEmploymentType() { return employmentType; }
    public void setEmploymentType(String employmentType) { this.employmentType = employmentType; }

    public String getTotalYears() { return totalYears; }
    public void setTotalYears(String totalYears) { this.totalYears = totalYears; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public boolean isCurrentlyWorking() { return currentlyWorking; }
    public void setCurrentlyWorking(boolean currentlyWorking) { this.currentlyWorking = currentlyWorking; }

    public String getResponsibilities() { return responsibilities; }
    public void setResponsibilities(String responsibilities) { this.responsibilities = responsibilities; }

    public String getAchievements() { return achievements; }
    public void setAchievements(String achievements) { this.achievements = achievements; }

    public String getWorkExperience() { return workExperience; }
    public void setWorkExperience(String workExperience) { this.workExperience = workExperience; }

    public Long getCafeId() { return cafeId; }
    public void setCafeId(Long cafeId) { this.cafeId = cafeId; }
}