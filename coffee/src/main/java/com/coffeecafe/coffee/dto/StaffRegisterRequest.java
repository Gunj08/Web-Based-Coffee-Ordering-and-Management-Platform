package com.coffeecafe.coffee.dto;

import org.springframework.web.multipart.MultipartFile;

public class StaffRegisterRequest {
    // Step 1: Personal
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String gender;
    private String dob;
    private String password;
    private String role;
    private Long cafeId;

    // Step 2: Address
    private String plotNo;
    private String area;
    private String city;
    private String pincode;

    // Step 3: Academic
    private String institution;
    private String degree;
    private String year;
    private MultipartFile govtProof;

    // Step 4: Work Experience
    private String jobTitle;
    private String companyName;
    private String employmentType;
    private String totalYears;
    private String startDate;
    private String endDate;
    private boolean currentlyWorking;
    private String responsibilities;
    private String achievements;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Long getCafeId() { return cafeId; }
    public void setCafeId(Long cafeId) { this.cafeId = cafeId; }

    public String getPlotNo() { return plotNo; }
    public void setPlotNo(String plotNo) { this.plotNo = plotNo; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

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

    public MultipartFile getGovtProof() { return govtProof; }
    public void setGovtProof(MultipartFile govtProof) { this.govtProof = govtProof; }

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
}