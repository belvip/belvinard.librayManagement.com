package com.belvinard.libraryManagementSystem.data;

import com.belvinard.libraryManagementSystem.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Data access class for managing the book collection.
 * Acts as a simple in-memory storage for books in the library.
 */
@Component
public class LibraryData {
    // List to store Book objects as an in-memory collection
    private final ArrayList<Book> bookCollection = new ArrayList<>();

    /**
     * Adds a new book to the book collection, checking for duplicates by ISBN.
     *
     * @param book The Book object to add to the collection.
     */
    public void addBook(Book book) {
        if (book != null) {
            // Check if the book's ISBN already exists in the collection
            boolean exists = bookCollection.stream()
                    .anyMatch(existingBook -> existingBook.getISBN().equals(book.getISBN()));

            if (exists) {
                // Throw an exception or display a message indicating ISBN conflict
                throw new IllegalArgumentException("A book with ISBN " + book.getISBN() + " already exists.");
            }

            // If ISBN is unique, add the book to the collection
            bookCollection.add(book);
        }
    }

    /**
     * Retrieves a book by its ISBN if it exists in the collection.
     *
     * @param ISBN The ISBN of the book to retrieve.
     * @return An Optional containing the found Book, or empty if not found.
     */
    public Optional<Book> getBookByISBN(String ISBN) {
        return bookCollection.stream()
                .filter(book -> book.getISBN().equals(ISBN))
                .findFirst();
    }

    /**
     * Removes a book from the collection by ISBN.
     *
     * @param ISBN The ISBN of the book to remove.
     * @return True if the book was removed, false if it was not found.
     */
    public boolean removeBook(String ISBN) {
        return bookCollection.removeIf(book -> book.getISBN().equals(ISBN));
    }

    /**
     * Retrieves the entire collection of books.
     *
     * @return A List of all books in the collection.
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookCollection); // Return a copy for immutability
    }
}
