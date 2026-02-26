package com.vish.librarymanagement.controller;

import com.vish.librarymanagement.model.Book;
import com.vish.librarymanagement.model.BorrowingRecord;
import com.vish.librarymanagement.model.Member;
import com.vish.librarymanagement.services.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LibraryController {
    //create a logger instance
    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    private LibraryService libraryService;

    //============Book Endpoints=============

    @GetMapping("/books")
    public ResponseEntity<ArrayList<Book>> getAllBooks () {
        ArrayList<Book> books = libraryService.getAllBooks();
        logger.info("The list of book returned: " + books);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    //Get book by id
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = libraryService.getBookById(id);
        logger.info("Get book by id: " + book);
        return book.map( value -> new ResponseEntity<>(value, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //add a new book
    @PostMapping("books/addbook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        libraryService.addBook(book);
        logger.info("New book added");
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book updateBook) {
        if(!libraryService.getBookById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updateBook.setId(id);
        libraryService.updateBook(updateBook);
        logger.info("Book updated" + updateBook);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<Book> bookDeleteById (@PathVariable Long id) {
        if(!libraryService.getBookById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        libraryService.deleteBookById(id);
        logger.info("Book has been deleted" );
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //get book by genre
    @GetMapping("books/genre")
    public ResponseEntity<Collection<Book>> getBooksByGenre (@RequestParam String genre) {
        Collection<Book> books = libraryService.getBooksByGenre(genre);
        logger.info("The books reterive for genre " +  genre);
        return new ResponseEntity<>(books, HttpStatus.OK);

    }

    @GetMapping("/books/author/{author}")
    public ResponseEntity<Collection<Book>> getBooksByAuthorAndGenre (@PathVariable String author , @RequestParam (required = false) String genre) {
        Collection <Book> allBooks = libraryService.getBooksByAuthorAndGenre(author, genre);
        logger.info("The books retrieved for the author and genre "+author+" - " + genre);
        return new ResponseEntity<>(allBooks,HttpStatus.OK);
    }


    //===========member controller============================

    //get all members
    @GetMapping("/members")
    public ResponseEntity<ArrayList<Member>> getAllMembers() {
        ArrayList<Member> membersList = libraryService.getAllMembers();
        logger.info("All members fetched " + membersList );
        return new ResponseEntity<>(membersList, HttpStatus.OK);
    }

    //get member by id
    @GetMapping("/members/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        Optional<Member> member  = libraryService.getMemberById(id);
        logger.info("Get member by id: " + id);

        return member.map((value)-> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    //add member
    @PostMapping("/members/addMember")
    public ResponseEntity<Member> addMember(@RequestBody Member newMember){
        libraryService.addMember(newMember);
        logger.info("New member added");
        return  new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    //update a member
    @PutMapping("/members/{id}")
    public ResponseEntity<Member> updateMemberById(@PathVariable Long id, @RequestBody Member updateMember) {

        if(!libraryService.getMemberById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        updateMember.setId(id);
        libraryService.updateMember(updateMember);
        logger.info("Member has been updated" + updateMember);
        return new ResponseEntity<>(updateMember, HttpStatus.OK);

    }

    //delete member
    @DeleteMapping("/members/{id}")
    public ResponseEntity<Member> deleteMemberById(@PathVariable Long id){

        if(!libraryService.getMemberById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        libraryService.deleteMember(id);
        logger.info("Member has been deleted ");

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // Borrowing Book Controller

    //get all borrowing  records
    @GetMapping("/borrowingRecords")
    public ResponseEntity<ArrayList<BorrowingRecord>> getAllBorrowingRecords() {
        ArrayList<BorrowingRecord> records = libraryService.getAllBorrowRecords();
        logger.info("all borrowing records fetched");
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    //borrow a book
    @PostMapping("/borrowingRecords/addRecord")
    public ResponseEntity<BorrowingRecord> borrowBook (@RequestBody BorrowingRecord newRecord) {

        libraryService.borrowBook(newRecord);
        logger.info("new borrow record created");
        return new ResponseEntity<>(newRecord, HttpStatus.CREATED);
    }

    @PutMapping ("/borrowingRecords/{id}")
    public ResponseEntity<BorrowingRecord> returnBook (@PathVariable Long id) {

        if(!libraryService.getRecordById(id).isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        libraryService.returnBook(id, LocalDate.now());
        logger.info("Book returned has been updated");
        return new ResponseEntity<>(HttpStatus.OK);


    }



}
