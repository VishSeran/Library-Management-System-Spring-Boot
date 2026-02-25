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

}
