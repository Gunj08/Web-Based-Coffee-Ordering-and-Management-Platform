package com.coffeecafe.coffee.entity;

import jakarta.persistence.*;

@Embeddable
public class AcademicInfo {
    private String institution;
    private String degree;
    private String yearOfPassing;

    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public String getYearOfPassing() { return yearOfPassing; }
    public void setYearOfPassing(String yearOfPassing) { this.yearOfPassing = yearOfPassing; }
}