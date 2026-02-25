package com.vish.librarymanagement.controller;

import com.vish.librarymanagement.model.Book;
import com.vish.librarymanagement.services.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
