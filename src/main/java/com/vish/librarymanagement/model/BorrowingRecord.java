package com.vish.librarymanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.sound.midi.MetaMessage;
import java.time.LocalDate;

public class BorrowingRecord {

    private Long id;

    @JsonProperty("bookId")
    private Long bookId;

    @JsonProperty("memberId")
    private Long memberId;

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

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId () {
        return bookId;
    }

    public void setBookId(Long bookId){
        this.bookId = bookId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMember(Long memberId) {
        this.memberId = memberId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // toString
    @Override
    public String toString() {
        return "BorrowingRecord{" +
                "id=" + id +
                ", book=" + book +
                ", member=" + member +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                ", dueDate=" + dueDate +
                '}';
    }

}
