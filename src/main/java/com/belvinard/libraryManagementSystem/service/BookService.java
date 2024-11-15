package com.belvinard.libraryManagementSystem.service;

import com.belvinard.libraryManagementSystem.data.LibraryData;
import com.belvinard.libraryManagementSystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookService {

    // Dependency on LibraryData, which manages data storage for books
    private final LibraryData libraryData;

    /**
     * Constructor-based dependency injection.
     * The libraryData dependency is automatically injected by Spring.
     *
     * @param libraryData A LibraryData object to handle book storage and retrieval.
     */
    @Autowired
    public BookService(LibraryData libraryData) {

        if (libraryData == null) {
            throw new IllegalArgumentException("LibraryData cannot be null.");
        }

        this.libraryData = libraryData;
    }

    /**
     * Adds a new book to the library.
     *
     * @param book The Book object to add to the library.
     */
    public void addBook(Book book) {

        libraryData.addBook(book);  // Delegate book addition to the data layer

    }
    public List<Book> getAllBooks() {
        return libraryData.getBookCollection(); // Assuming `getBookCollection()` returns the list of books.
    }

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

    /*
    * ============================= Update book starts =============================
    */

    public void updateBook(Book updatedBook) {
        for (int i = 0; i < libraryData.getBookCollection().size(); i++) {
            if (libraryData.getBookCollection().get(i).getISBN().equals(updatedBook.getISBN())) {
                libraryData.getBookCollection().set(i, updatedBook);
                return;
            }
        }
        throw new IllegalArgumentException("Book with ISBN " + updatedBook.getISBN() + " not found.");
    }

    public Book getBookByISBN(String isbn) {
        return libraryData.getBookCollection()
                .stream()
                .filter(book -> book.getISBN().equals(isbn))
                .findFirst()
                .orElse(null);
    }


    /*
    * ============================= Update book starts =============================
    */

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */





}