package com.vish.librarymanagement.services;

import com.vish.librarymanagement.model.Book;
import com.vish.librarymanagement.model.BorrowingRecord;
import com.vish.librarymanagement.model.Member;
import org.springframework.stereotype.Service;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class LibraryService {

    private ArrayList<Book> booksList = new ArrayList<>();
    private ArrayList<Member> membersList = new ArrayList<>();
    private ArrayList<BorrowingRecord> borrowingRecordList = new ArrayList<>();

    //===========Book CRUD===============================

    //get all books
    public ArrayList<Book> getAllBooks() {
        return booksList;
    }

    //Get book by ID
    //we use optional to store whether book can be available or not
    public Optional<Book> getBookById(Long id){
        return booksList.stream().
                filter(book -> book.getId().equals(id)).findFirst();
    }

    //add a new book
    public void addBook (Book book){
        booksList.add(book);
    }

    //update a book
    public void updateBook(Book updatedbook){
        for (int i = 0; i< booksList.size(); i++){
            Book book = booksList.get(i);
            if(book.getId().equals(updatedbook.getId())){
                booksList.set(i, updatedbook);
                break;
            }
        }
    }

    //Delete a book by ID
    public void deleteBookById(Long id){
        booksList.removeIf(book -> book.getId().equals(id));
    }

    //=========Member CRUD======================

    //Get all members
    public ArrayList<Member> getAllMembers() {
        return membersList;
    }

    //Get member by ID
    public Optional<Member> getMemberById(Long id){
        return membersList.stream().filter(
                member -> member.getId().equals(id)
        ).findFirst();
    }

    //Add a new member
    public void addMember (Member member) {
        membersList.add(member);
    }

    //update a member
    public void updateMember(Member updatedMember) {
        for(int i =0; i < membersList.size(); i++){
            Member member = membersList.get(i);
            if(member.getId().equals(updatedMember.getId())){
                membersList.set(i, updatedMember);
                break;
            }
        }
    }

    //Delete member by ID
    public void deleteMember(Long id) {
        membersList.removeIf(member -> member.getId().equals(id));
    }

    // ==================== BorrowingRecord Methods ====================

    //Get all borrow records
    public ArrayList<BorrowingRecord> getAllBorrowRecords() {
        return borrowingRecordList;
    }

    // borrow a book (create a new borrow record)
    public void borrowBook (BorrowingRecord borrowingRecord) {

        borrowingRecord.setBorrowDate(LocalDate.now());
        borrowingRecord.setDueDate(LocalDate.now().plusDays(14));
        borrowingRecordList.add(borrowingRecord);

        //when borrow a book then that book needs to remove from booklist
        Book borrowedBook = borrowingRecord.getBook();
        borrowedBook.setAvailableCopies(borrowedBook.getAvailableCopies()-1);
    }

    // Return a book (update the borrowing record with the return date)
    public void returnBook (BorrowingRecord updatedborrowingRecord) {
        updatedborrowingRecord.setReturnDate(LocalDate.now());
        for( int i =0; i< borrowingRecordList.size(); i++) {
            BorrowingRecord borrowingRecord = borrowingRecordList.get(i);
            if(borrowingRecord.getId().equals(updatedborrowingRecord.getId())){
                borrowingRecordList.set(i, updatedborrowingRecord);
                break;
            }
        }

        // now increase the number of copies of the book
        Book returnedBook = updatedborrowingRecord.getBook();
        returnedBook.setAvailableCopies(returnedBook.getAvailableCopies() + 1);
    }

}
