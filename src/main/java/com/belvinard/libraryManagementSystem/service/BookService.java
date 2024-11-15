package com.belvinard.libraryManagementSystem.service;

import com.belvinard.libraryManagementSystem.data.LibraryData;
import com.belvinard.libraryManagementSystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
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

    /*
     * ============================= Searching method  book starts =============================
     */

          // 1. ===================== Linear search for books by title, author, or ISBN =====================

    public List<Book> searchBooksLinear(String query, String searchBy) {
        List<Book> result = new ArrayList<>();

        for (Book book : libraryData.getBookCollection()) {
            switch (searchBy.toLowerCase()) {
                case "title":
                    if (book.getTitle().equalsIgnoreCase(query)) {
                        result.add(book);
                    }
                    break;
                case "author":
                    if (book.getAuthor().equalsIgnoreCase(query)) {
                        result.add(book);
                    }
                    break;
                case "isbn":
                    if (book.getISBN().equals(query)) {
                        result.add(book);
                    }
                    break;
                default:
                    System.out.println("Invalid search type. Please use 'title', 'author', or 'isbn'.");
            }
        }

        return result;
    }


    // 2. ===================== Binary search for books by title, author, or ISBN =====================

    // Binary search for books by ISBN
    public Book searchBookBinary(String query, String searchBy) {
        List<Book> booksSorted = new ArrayList<>(libraryData.getBookCollection());

        if (searchBy.equalsIgnoreCase("title")) {
            booksSorted.sort(Comparator.comparing(Book::getTitle));
        } else if (searchBy.equalsIgnoreCase("author")) {
            booksSorted.sort(Comparator.comparing(Book::getAuthor));
        } else {
            booksSorted.sort(Comparator.comparing(Book::getISBN));
        }

        int left = 0;
        int right = booksSorted.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Book midBook = booksSorted.get(mid);

            // Searching by the selected field
            if (searchBy.equalsIgnoreCase("title") && midBook.getTitle().equalsIgnoreCase(query)) {
                return midBook;
            } else if (searchBy.equalsIgnoreCase("author") && midBook.getAuthor().equalsIgnoreCase(query)) {
                return midBook;
            } else if (searchBy.equalsIgnoreCase("isbn") && midBook.getISBN().equals(query)) {
                return midBook;
            }

            if (searchBy.equalsIgnoreCase("title") && midBook.getTitle().compareToIgnoreCase(query) < 0 ||
                    searchBy.equalsIgnoreCase("author") && midBook.getAuthor().compareToIgnoreCase(query) < 0 ||
                    searchBy.equalsIgnoreCase("isbn") && midBook.getISBN().compareTo(query) < 0) {
                left = mid + 1; // Search the right half
            } else {
                right = mid - 1; // Search the left half
            }
        }

        return null; // Return null if no match is found
    }


    /*
     * ============================= Searching method book ends =============================
     */

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

    /*
     * ============================= Sorting method  book starts =============================
     */

    public static class SortBooks {

        // Bubble Sort: Sort by title (ascending)
        public static void bubbleSortByTitle(List<Book> books) {
            int n = books.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (books.get(j).getTitle().compareTo(books.get(j + 1).getTitle()) > 0) {
                        Book temp = books.get(j);
                        books.set(j, books.get(j + 1));
                        books.set(j + 1, temp);
                    }
                }
            }
        }

        // Selection Sort: Sort by publication year (ascending)
        public static void selectionSortByYear(List<Book> books) {
            int n = books.size();
            for (int i = 0; i < n - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < n; j++) {
                    if (books.get(j).getPublicationYear() < books.get(minIdx).getPublicationYear()) {
                        minIdx = j;
                    }
                }
                Book temp = books.get(i);
                books.set(i, books.get(minIdx));
                books.set(minIdx, temp);
            }
        }

        // QuickSort: Sort by author (alphabetically)
        public static void quickSortByAuthor(List<Book> books, int low, int high) {
            if (low < high) {
                int pi = partition(books, low, high);
                quickSortByAuthor(books, low, pi - 1);
                quickSortByAuthor(books, pi + 1, high);
            }
        }

        private static int partition(List<Book> books, int low, int high) {
            Book pivot = books.get(high);
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (books.get(j).getAuthor().compareTo(pivot.getAuthor()) < 0) {
                    i++;
                    Book temp = books.get(i);
                    books.set(i, books.get(j));
                    books.set(j, temp);
                }
            }
            Book temp = books.get(i + 1);
            books.set(i + 1, books.get(high));
            books.set(high, temp);
            return i + 1;
        }

        // Bubble Sort: Sort by publication year (ascending)
        public static void bubbleSortByYear(List<Book> books) {
            int n = books.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (books.get(j).getPublicationYear() > books.get(j + 1).getPublicationYear()) {
                        Book temp = books.get(j);
                        books.set(j, books.get(j + 1));
                        books.set(j + 1, temp);
                    }
                }
            }
        }

        // QuickSort: Sort by publication year (ascending)
        public static void quickSortByYear(List<Book> books, int low, int high) {
            if (low < high) {
                int pi = partitionByYear(books, low, high);
                quickSortByYear(books, low, pi - 1);
                quickSortByYear(books, pi + 1, high);
            }
        }

        private static int partitionByYear(List<Book> books, int low, int high) {
            Book pivot = books.get(high);
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (books.get(j).getPublicationYear() < pivot.getPublicationYear()) {
                    i++;
                    Book temp = books.get(i);
                    books.set(i, books.get(j));
                    books.set(j, temp);
                }
            }
            Book temp = books.get(i + 1);
            books.set(i + 1, books.get(high));
            books.set(high, temp);
            return i + 1;
        }

        // Selection Sort: Sort by author (alphabetically)
        public static void selectionSortByAuthor(List<Book> books) {
            int n = books.size();
            for (int i = 0; i < n - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < n; j++) {
                    if (books.get(j).getAuthor().compareTo(books.get(minIdx).getAuthor()) < 0) {
                        minIdx = j;
                    }
                }
                Book temp = books.get(i);
                books.set(i, books.get(minIdx));
                books.set(minIdx, temp);
            }
        }

        // Bubble Sort: Sort by author (alphabetically)
        public static void bubbleSortByAuthor(List<Book> books) {
            int n = books.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (books.get(j).getAuthor().compareTo(books.get(j + 1).getAuthor()) > 0) {
                        Book temp = books.get(j);
                        books.set(j, books.get(j + 1));
                        books.set(j + 1, temp);
                    }
                }
            }
        }

        // Selection Sort: Sort by title (alphabetically)
        public static void selectionSortByTitle(List<Book> books) {
            int n = books.size();
            for (int i = 0; i < n - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < n; j++) {
                    if (books.get(j).getTitle().compareTo(books.get(minIdx).getTitle()) < 0) {
                        minIdx = j;
                    }
                }
                Book temp = books.get(i);
                books.set(i, books.get(minIdx));
                books.set(minIdx, temp);
            }
        }

        // QuickSort: Sort by title (alphabetically)
        public static void quickSortByTitle(List<Book> books, int low, int high) {
            if (low < high) {
                int pi = partitionByTitle(books, low, high);
                quickSortByTitle(books, low, pi - 1);
                quickSortByTitle(books, pi + 1, high);
            }
        }

        private static int partitionByTitle(List<Book> books, int low, int high) {
            Book pivot = books.get(high);
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (books.get(j).getTitle().compareTo(pivot.getTitle()) < 0) {
                    i++;
                    Book temp = books.get(i);
                    books.set(i, books.get(j));
                    books.set(j, temp);
                }
            }
            Book temp = books.get(i + 1);
            books.set(i + 1, books.get(high));
            books.set(high, temp);
            return i + 1;
        }
    }


    /*
     * ============================= Sorting method  book ends =============================
     */





}