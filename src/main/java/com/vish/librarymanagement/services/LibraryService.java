package com.vish.librarymanagement.services;

import com.vish.librarymanagement.model.Book;
import com.vish.librarymanagement.model.BorrowingRecord;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class LibraryService {

    private ArrayList<Book> booksList = new ArrayList<>();
    private ArrayList<Member> membersList = new ArrayList<>();
    private ArrayList<BorrowingRecord> borrowingRecordList = new ArrayList<>();


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

}
