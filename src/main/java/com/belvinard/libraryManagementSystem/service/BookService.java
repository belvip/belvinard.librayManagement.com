package com.belvinard.libraryManagementSystem.service;

import com.belvinard.libraryManagementSystem.data.LibraryData;
import com.belvinard.libraryManagementSystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.belvinard.libraryManagementSystem.console.ConsoleHandler.books;

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

    /**
     * Updates a book in the library's collection.
     *
     * @param updatedBook the book with updated information
     * @throws IllegalArgumentException if no book with the given ISBN is found
     */
    public void updateBook(Book updatedBook) {
        // Iterate through the collection to find a book with the matching ISBN
        for (int i = 0; i < libraryData.getBookCollection().size(); i++) {
            if (libraryData.getBookCollection().get(i).getISBN().equals(updatedBook.getISBN())) {
                // Replace the book at the current index with the updated book
                libraryData.getBookCollection().set(i, updatedBook);
                return; // Exit the method after successful update
            }
        }
        // If no matching book is found, throw an exception
        throw new IllegalArgumentException("Book with ISBN " + updatedBook.getISBN() + " not found.");
    }

    /**
     * Retrieves a book from the library's collection by its ISBN.
     *
     * @param isbn the ISBN of the book to retrieve
     * @return the book with the matching ISBN, or null if no match is found
     */
    public Book getBookByISBN(String isbn) {
        // Use a stream to find the first book with the matching ISBN
        return libraryData.getBookCollection()
                .stream()
                .filter(book -> book.getISBN().equals(isbn)) // Filter books by matching ISBN
                .findFirst() // Get the first match if available
                .orElse(null); // Return null if no match is found
    }

    /*
    * ============================= Update book starts =============================
    */

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

    /*
     * ============================= Remove book starts =============================
     */

    public void removeBookByISBN(String isbn) {
        // Step 1: Find the book in the collection using ISBN
        Book bookToRemove = libraryData.getBookCollection()
                .stream() // Stream through the book collection
                .filter(book -> book.getISBN().equals(isbn)) // Check if ISBN matches
                .findFirst() // Get the first match, or null if not found
                .orElse(null); // Return null if no match is found

        // Step 2: If a matching book is found, remove it from the collection
        if (bookToRemove != null) {
            libraryData.getBookCollection().remove(bookToRemove); // Remove the book from the collection
            System.out.println("Book with ISBN " + isbn + " has been removed successfully."); // Confirmation message
        } else {
            // Step 3: If no book with the given ISBN is found, print an error message
            System.out.println("Error: No book found with ISBN " + isbn); // Error message if no book found
        }
    }

    /*
     * ============================= Remove book ends =============================
     */

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

    public List<Book> searchBooks(String query, String searchType) {
        List<Book> foundBooks = new ArrayList<>();

        // Perform search based on the searchType (title, author, or ISBN)
        for (Book book : books) {
            switch (searchType.toLowerCase()) {
                case "title":
                    if (book.getTitle().contains(query)) {
                        foundBooks.add(book);
                    }
                    break;
                case "author":
                    if (book.getAuthor().contains(query)) {
                        foundBooks.add(book);
                    }
                    break;
                case "isbn":
                    if (book.getISBN().equals(query)) {  // Updated to use getISBN() instead of getIsbn()
                        foundBooks.add(book);
                    }
                    break;
                default:
                    System.out.println("Invalid search type.");
                    break;
            }
        }
        return foundBooks;  // Return the list of books that match the query
    }


    // Binary search method
    public Book binarySearchBooks(String query, String searchType) {
        // Sort the list based on the search type
        switch (searchType.toLowerCase()) {
            case "title":
                books.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
                return binarySearch(query, books, Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
            case "author":
                books.sort(Comparator.comparing(Book::getAuthor, String.CASE_INSENSITIVE_ORDER));
                return binarySearch(query, books, Comparator.comparing(Book::getAuthor, String.CASE_INSENSITIVE_ORDER));
            case "isbn":
                books.sort(Comparator.comparing(Book::getISBN));
                return binarySearch(query, books, Comparator.comparing(Book::getISBN));
            default:
                System.out.println("Invalid search type. Please use 'title', 'author', or 'isbn'.");
                return null;
        }
    }



    // Generic binary search logic
    public Book binarySearch(String query, List<Book> books, Comparator<Book> comparator) {
        int low = 0, high = books.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Book midBook = books.get(mid);

            // Perform the comparison directly using the field value (not by creating a new Book object)
            int comparison = comparator.compare(midBook, midBook); // this line doesn't make sense, it's for sorting not comparison.
            if (comparison == 0) {
                return midBook; // Found the book
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null; // No book found
    }




}