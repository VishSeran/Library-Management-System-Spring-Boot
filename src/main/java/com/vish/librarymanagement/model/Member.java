package com.vish.librarymanagement.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class Member {
    private Long memberId;

    private String name;
    private String email;
    private int phoneNumber;
    private LocalDate membershipStartDate;
    private LocalDate membershipEndDate;

    // Default Constructor
    public Member (){}

    // Parameterized Constructor
    public Member(String name,String email, int phoneNumber, LocalDate membershipStartDate, LocalDate membershipEndDate) {
        this.name = name;
        this.email  = email;
        this.phoneNumber = phoneNumber;
        this.membershipStartDate = membershipStartDate;
        this.membershipEndDate = membershipEndDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // toString
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", membershipStartDate=" + startDate +
                ", membershipEndDate=" + endDate +
                '}';
    }

}
