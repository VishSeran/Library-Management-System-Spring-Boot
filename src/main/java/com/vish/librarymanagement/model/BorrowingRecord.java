package com.vish.librarymanagement.model;

import javax.sound.midi.MetaMessage;
import java.time.LocalDate;

public class BorrowingRecord {

    private Long id;

    private Book book;
    private Member member;

    private LocalDate borrowDate;
    private LocalDate returnDate;
    private LocalDate dueDate;

    //Default constructor
    public BorrowingRecord() {

    }

    // Parameterized constructor
    public BorrowingRecord (Book book, Member member, LocalDate borrowDate, LocalDate returnDate, LocalDate dueDate) {
        this.book = book;
        this.member = member;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.dueDate = dueDate;

    }



}
