package com.vish.librarymanagement.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class Member {
    private Long memberId;

    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate membershipStartDate;
    private LocalDate membershipEndDate;

    // Default Constructor
    public Member (){}

    // Parameterized Constructor
    public Member(String name,String email, String phoneNumber, LocalDate membershipStartDate, LocalDate membershipEndDate) {
        this.name = name;
        this.email  = email;
        this.phoneNumber = phoneNumber;
        this.membershipStartDate = membershipStartDate;
        this.membershipEndDate = membershipEndDate;
    }

    // Getters and Setters
    public Long getId() {
        return memberId;
    }

    public void setId(Long id) {
        this.memberId = id;
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
        return membershipStartDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.membershipStartDate = startDate;
    }

    public LocalDate getEndDate() {
        return membershipEndDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.membershipEndDate = endDate;
    }

    // toString
    @Override
    public String toString() {
        return "Member{" +
                "id=" + memberId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", membershipStartDate=" + membershipStartDate +
                ", membershipEndDate=" + membershipEndDate +
                '}';
    }

}
