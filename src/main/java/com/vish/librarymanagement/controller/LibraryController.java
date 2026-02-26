package com.vish.librarymanagement.controller;

import com.vish.librarymanagement.model.Book;
import com.vish.librarymanagement.model.Member;
import com.vish.librarymanagement.services.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    @PutMapping("/members/addMember")
    public ResponseEntity<Member> addMember(@RequestBody Member newMember){
        libraryService.addMember(newMember);
        logger.info("New member added");
        return  new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

}
