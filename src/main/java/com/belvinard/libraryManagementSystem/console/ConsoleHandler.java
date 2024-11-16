package com.belvinard.libraryManagementSystem.console;

import com.belvinard.libraryManagementSystem.model.Book;
import com.belvinard.libraryManagementSystem.service.BookService;
import com.belvinard.libraryManagementSystem.service.LibraryService;
import com.belvinard.libraryManagementSystem.util.BookSortService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class ConsoleHandler {
    public static List<Book> books = new ArrayList<>();
    private final BookService bookService;
    private static Scanner scanner;
    private final BookSortService bookSortService;
    private LibraryService libraryService;

    //private LibraryService libraryService;

    


    public ConsoleHandler(BookService bookService, LibraryService libraryService,Scanner scanner) {
        this.bookService = bookService;
        this.books = bookService.getAllBooks();
        this.libraryService = libraryService;// Fetch books from BookService
        this.scanner = new Scanner(System.in);  // Initialize scanner
        this.bookSortService = new BookSortService(books);
    }




    /*
    * ============================= Console starts =============================
    */

    /**
     * Starts the console-based library management system.
     * Continues running until the user chooses to exit.
     */
    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getChoiceInput(); // Get the user's choice
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    updateBook();
                    break;
                case 4:
                    removeBook(); // Call the method to remove a book
                    break;
                case 5:
                    searchBooks();
                    break;
                case 6:
                    sortBooks();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    return;
            }
        }
    }

    /*
    * ============================= Console ends =============================
    */

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

    /*
    * ============================= Display Menu starts =============================
    */

    /**
     * Displays the main menu for the library system.
     */
    private void displayMenu() {
        System.out.println("\n----- The Library Management System Portal -----");
        System.out.println(
                "\nPress 1 for Adding Book \n" +
                "Press 2 for Displaying All Books \n" +
                        "Press 3 for Updating Book \n" +
                        "Press 4 for Removing Book \n" +
                        "Press 5 for Searching Book \n" +
                        "Press 6 for Sorting Book \n" +
                        "Press 7 for Exiting the portal\n"
        );
        System.out.print("Enter your choice: ");
    }

    /*
    * ============================= Display Menu ends =============================
    */

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

    /*
    * ============================= Add book starts =============================
    */

    /**
     * Handles adding a new book by gathering details from the user.
     * Will prompt the user to retry if input is invalid.
     */
    private void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter publication year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("\n========= Details Book entered : ========== \n");
        System.out.println("Book Title: " + title);
        System.out.println("Book Author: " + author);
        System.out.println("Book Genre: " + genre);
        System.out.println("Book ISBN: " + isbn);
        System.out.println("Book Year: " + year);

        try {
            Book book = new Book(title, author, genre, isbn, year);
            libraryService.addBook(book); // Use libraryService for adding the book
            System.out.println("Book added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    /**
     * Retrieves book details from user input, validating each entry.
     *
     * @return a Book object with the entered details.
     */
    private Book getBookDetails() {
        try {
            System.out.print("Enter book title: ");
            String title = scanner.nextLine();
            System.out.print("Enter book author: ");
            String author = scanner.nextLine();
            System.out.print("Enter book genre: ");
            String genre = scanner.nextLine();
            System.out.print("Enter book ISBN: ");
            String isbn = scanner.nextLine();
            System.out.print("Enter publication year: ");
            int year = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
            book.setISBN(isbn);
            book.setPublicationYear(year);

            return book;
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct values.");
            scanner.nextLine(); // Clear the invalid input
            return null;
        }
    }

    /**
     * Retrieves and validates the user's choice from the main menu.
     *
     * @return an integer representing the user's menu choice.
     */
    private int getChoiceInput() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();  // Clear invalid input
            // return -1;  // Return an invalid choice to prompt re-entry
            return scanner.nextInt();
        }
    }

    /*
    * ============================= Add book ends =============================
    */

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

    /*
    * ============================= Display all book starts =============================
    */
    private void displayAllBooks() {
        List<Book> books = bookService.getAllBooks(); // Get the list of books from the service

        if (books.isEmpty()) {
            System.out.println("No books have been added yet.");
        } else {
            System.out.println("\n===== List of Books =====");
            for (Book book : books) {
                System.out.println(book); // Leverage the custom `toString()` method
                System.out.println("-------------------------");
            }
        }
    }

    /*
    * ============================= Display all book ends =============================
    */

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

    /*
    * ============================= Update book starts =============================
    */

    /**
     * Updates a book's information based on user input.
     * Allows updating the title, author, genre, or publication year.
     * Validates inputs and ensures changes are only saved if valid.
     */
    private void updateBook() {
        // Prompt the user for the ISBN of the book they want to update
        System.out.print("Enter the ISBN of the book to update: ");
        String isbn = scanner.nextLine();

        // Retrieve the book matching the provided ISBN
        Book bookToUpdate = bookService.getBookByISBN(isbn);

        // Check if the book exists; if not, display an error and exit the method
        if (bookToUpdate == null) {
            System.out.println("Error: No book found with ISBN " + isbn);
            return;
        }

        // Display the book details to the user
        System.out.println("Book found: ");
        System.out.println(bookToUpdate);

        // Provide the user with options for which attribute they want to update
        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Genre");
        System.out.println("4. Publication Year");
        System.out.println("5. Cancel");

        // Prompt the user for their choice
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character left by nextInt()

        // Flag to determine if any updates were successfully made
        boolean isUpdated = false;

        // Handle the user's choice
        switch (choice) {
            case 1:
                // Update the book's title
                System.out.print("Enter new title: ");
                String newTitle = scanner.nextLine();
                try {
                    bookToUpdate.setTitle(newTitle);
                    isUpdated = true;
                } catch (IllegalArgumentException e) {
                    // Display error message for invalid title
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                // Update the book's author
                System.out.print("Enter new author: ");
                String newAuthor = scanner.nextLine();
                try {
                    bookToUpdate.setAuthor(newAuthor);
                    isUpdated = true;
                } catch (IllegalArgumentException e) {
                    // Display error message for invalid author
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                // Update the book's genre
                System.out.print("Enter new genre: ");
                String newGenre = scanner.nextLine();
                try {
                    bookToUpdate.setGenre(newGenre);
                    isUpdated = true;
                } catch (IllegalArgumentException e) {
                    // Display error message for invalid genre
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                // Update the book's publication year
                System.out.print("Enter new publication year: ");
                int newYear = scanner.nextInt();
                scanner.nextLine(); // Consume newline character left by nextInt()
                try {
                    bookToUpdate.setPublicationYear(newYear);
                    isUpdated = true;
                } catch (IllegalArgumentException e) {
                    // Display error message for invalid year
                    System.out.println(e.getMessage());
                }
                break;
            case 5:
                // Cancel the update operation
                System.out.println("Update canceled.");
                return;
            default:
                // Handle invalid choices
                System.out.println("Invalid choice. Update canceled.");
                return;
        }

        // Save changes only if a valid update was made
        if (isUpdated) {
            bookService.updateBook(bookToUpdate);
            System.out.println("Book updated successfully.");
        }
    }

    /*
    * ============================= Update book ends =============================
    */

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

    /*
     * ============================= Remove book starts =============================
     */

    private void removeBook() {
        System.out.print("Enter the ISBN of the book to remove: ");
        String isbn = scanner.nextLine();

        // Call the BookService to remove the book by ISBN
        bookService.removeBookByISBN(isbn);
    }

    /*
     * ============================= Remove book ends =============================
     */

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

    /*
     * ============================= Search book starts =============================
     */
    private void searchBooks() {
        System.out.println("Enter search type (title, author, isbn): ");
        String searchType = scanner.nextLine().trim();
        System.out.println("Enter search query: ");
        String query = scanner.nextLine().trim();

        System.out.println("Choose search method: ");
        System.out.println("1. Linear Search");
        System.out.println("2. Binary Search");
        int methodChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Debug: searchType=" + searchType + ", query=" + query + ", methodChoice=" + methodChoice);

        if (methodChoice == 1) {
            List<Book> foundBooks = bookService.searchBooks(query, searchType);
            if (foundBooks.isEmpty()) {
                System.out.println("No books found with the given query.");
            } else {
                System.out.println("Books found:");
                for (Book book : foundBooks) {
                    System.out.println(book);
                }
            }
        } else if (methodChoice == 2) {
            Book foundBook = bookService.binarySearchBooks(query, searchType);
            if (foundBook == null) {
                System.out.println("No books found with the given query.");
            } else {
                System.out.println("Book found:");
                System.out.println(foundBook);
            }
        } else {
            System.out.println("Invalid choice.");
        }
    }



    /*
     * ============================= Search book ends =============================
     */

    /*
     * ============================= Sort book starts =============================
     */
    public void sortBooks() {
        if (this.libraryService == null) {
            this.libraryService = new LibraryService(); // Initialize it if not done yet
        }

        System.out.println("How would you like to sort the books?");
        System.out.println("1. Sort by Title");
        System.out.println("2. Sort by Author");
        System.out.println("3. Sort by Publication Year");
        System.out.println("4. Sort by Genre");
        System.out.println("5. Sort by ISBN");
        System.out.print("Enter your choice: ");
        int sortChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Which sorting algorithm would you like to use?");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. QuickSort");
        System.out.print("Enter your choice: ");
        int algoChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Fetch the book list from the library service
        List<Book> books = libraryService.getBooks();

        if (books == null || books.isEmpty()) {
            System.out.println("No books available to sort.");
            return;
        }

        switch (sortChoice) {
            case 1: // Sort by Title
                if (algoChoice == 1) BookSortService.bubbleSortByTitle(books);
                else if (algoChoice == 2) BookSortService.selectionSortByTitle(books);
                else if (algoChoice == 3) BookSortService.quickSortByTitle(books, 0, books.size() - 1);
                break;

            case 2: // Sort by Author
                if (algoChoice == 1) BookSortService.bubbleSortByAuthor(books);
                else if (algoChoice == 2) BookSortService.selectionSortByAuthor(books);
                else if (algoChoice == 3) BookSortService.quickSortByAuthor(books, 0, books.size() - 1);
                break;

            case 3: // Sort by Publication Year
                if (algoChoice == 1) BookSortService.bubbleSortByYear(books);
                else if (algoChoice == 2) BookSortService.selectionSortByYear(books);
                else if (algoChoice == 3) BookSortService.quickSortByYear(books, 0, books.size() - 1);
                break;

            case 4: // Sort by Genre
                if (algoChoice == 3) BookSortService.quickSortByGenre(books, 0, books.size() - 1);
                else System.out.println("Only QuickSort is supported for Genre.");
                break;

            case 5: // Sort by ISBN
                if (algoChoice == 1) BookSortService.bubbleSortByIsbn(books);
                else if (algoChoice == 2) BookSortService.selectionSortByIsbn(books);
                else if (algoChoice == 3) BookSortService.quickSortByIsbn(books, 0, books.size() - 1);
                break;

            default:
                System.out.println("Invalid choice, returning to the main menu.");
                return;
        }

        // Display sorted books
        System.out.println("\n===== Sorted List of Books =====");
        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("Books in the collection: " + books.size());

    }


    /*
     * ============================= Sort book ends =============================
     */



}
