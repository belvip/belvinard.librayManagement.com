package com.belvinard.libraryManagementSystem.data;

import com.belvinard.libraryManagementSystem.exception.BookAlreadyExistsException;
import com.belvinard.libraryManagementSystem.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Data access class for managing the book collection.
 * Acts as a simple in-memory storage for books in the library.
 */
@Component
public class LibraryData {

    private static final Logger logger = LoggerFactory.getLogger(LibraryData.class);

    // HashMap to store Book objects, with ISBN as the key for fast access
    private final Map<String, Book> bookCollection = new HashMap<>();

    /**
     * Adds a new book to the book collection, checking for duplicates by ISBN.
     *
     * @param book The Book object to add to the collection.
     */
    public void addBook(Book book) {
        if (book != null) {
            // Check if the ISBN already exists in the collection
            if (bookCollection.containsKey(book.getISBN())) {
                logger.error("Attempted to add a duplicate book with ISBN {}", book.getISBN());
                throw new BookAlreadyExistsException("A book with ISBN " + book.getISBN() + " already exists.");
            }

            // Add the book to the collection
            bookCollection.put(book.getISBN(), book);
            logger.info("Book with ISBN {} added successfully.", book.getISBN());
        }
    }

    /**
     * Retrieves a book by its ISBN if it exists in the collection.
     *
     * @param ISBN The ISBN of the book to retrieve.
     * @return An Optional containing the found Book, or empty if not found.
     */
    public Optional<Book> getBookByISBN(String ISBN) {
        return Optional.ofNullable(bookCollection.get(ISBN));
    }

    /**
     * Removes a book from the collection by ISBN.
     *
     * @param ISBN The ISBN of the book to remove.
     * @return True if the book was removed, false if it was not found.
     */
    public boolean removeBook(String ISBN) {
        if (bookCollection.containsKey(ISBN)) {
            bookCollection.remove(ISBN);
            logger.info("Book with ISBN {} removed successfully.", ISBN);
            return true;
        } else {
            logger.warn("Attempted to remove a book with ISBN {}, but it was not found.", ISBN);
            return false;
        }
    }

    /**
     * Retrieves the entire collection of books.
     *
     * @return A List of all books in the collection.
     */
    public List<Book> getAllBooks() {
        if (bookCollection.isEmpty()) {
            logger.info("No books available in the collection.");
        }
        return List.copyOf(bookCollection.values()); // Return an immutable list
    }

    // Getter for internal bookCollection if needed (for testing or internal use)
    public Map<String, Book> getBookCollection() {
        return Collections.unmodifiableMap(bookCollection); // Return an unmodifiable map
    }
}
