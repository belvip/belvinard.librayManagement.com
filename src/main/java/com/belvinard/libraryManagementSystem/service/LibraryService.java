package com.belvinard.libraryManagementSystem.service;

import com.belvinard.libraryManagementSystem.model.Book;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {


    // A field to hold the list of books
    private List<Book> books;

    // Constructor to initialize books
    public LibraryService() {
        this.books = new ArrayList<>();  // Initialize books list as an empty ArrayList
        // Add some books to the list for testing
        // this.books.add(new Book("Java", "Belvi", "Full-stack Development", "12345", 2020));
        // this.books.add(new Book("Python", "Toto", "Back-end Development", "14582", 2004));
    }

    // The method to get the list of books
    public List<Book> getBooks() {
        return books;
    }

    // Method to add a book to the list
    public void addBook(Book book) {
        books.add(book);
    }

    // Other methods related to library management can go here
}