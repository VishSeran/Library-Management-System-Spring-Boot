package com.vish.librarymanagement.services;

import com.vish.librarymanagement.model.Book;
import com.vish.librarymanagement.model.BorrowingRecord;
import com.vish.librarymanagement.model.Member;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

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

    //get book by genre
    public Collection<Book> getBooksByGenre(String genre) {
        Collection<Book> allBooks = booksList;
        return allBooks.stream().filter(book-> book.getGenre().equalsIgnoreCase(genre)).collect(Collectors.toList());
    }

    //filter by author and optional filter by genre
    public Collection<Book> getBooksByAuthorAndGenre(String author, String genre) {
        Collection<Book> allBooks = booksList;
        return allBooks.stream().filter(book->book.getAuthor().equalsIgnoreCase(author)).
                filter(book  ->book.getGenre().equalsIgnoreCase(genre)).collect(Collectors.toList());
    }

    public Collection<Book> getBooksByDueDate (LocalDate dueDate) {
        ArrayList<Book> dueBooks =  new ArrayList<>();
        Collection <BorrowingRecord> allRecords = borrowingRecordList;
        Collection<BorrowingRecord> filterdRecords =  allRecords.stream().filter(borrowingRecord -> borrowingRecord.getDueDate().equals(dueDate))
                .toList();

        for (BorrowingRecord record : filterdRecords) {
            Book book = booksList.get(Math.toIntExact(record.getBookId()));
            if(book != null){
                dueBooks.add(book);
            }
        }
        return dueBooks;

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

    //get record by Id
    public Optional<BorrowingRecord> getRecordById(Long id){

        return borrowingRecordList.stream().filter(record -> record.getId().equals(id)).
                findFirst();
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
    public void returnBook (Long recordId, LocalDate returnDate) {

        for( BorrowingRecord borrow : borrowingRecordList){
            if(borrow.getId().equals(recordId)){
                borrow.setReturnDate(returnDate);

                // now increase the number of copies of the book
                Book returnedBook = borrow.getBook();
                returnedBook.setAvailableCopies(returnedBook.getAvailableCopies() + 1);
            }
        }


    }

}
