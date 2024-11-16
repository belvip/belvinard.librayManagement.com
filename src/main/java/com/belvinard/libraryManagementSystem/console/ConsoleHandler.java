package com.belvinard.libraryManagementSystem.console;

import com.belvinard.libraryManagementSystem.model.Book;
import com.belvinard.libraryManagementSystem.service.BookService;
import com.belvinard.libraryManagementSystem.util.BookSortService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleHandler {
    public static List<Book> books = new ArrayList<>();
    private final BookService bookService;
    private static Scanner scanner;
    private final BookSortService bookSortService;

    public ConsoleHandler(BookService bookService, Scanner scanner) {
        this.bookService = bookService;
        this.books = bookService.listBooks();  // Utiliser listBooks() à la place de getAllBooks()
        this.scanner = scanner;
        this.bookSortService = new BookSortService(books);
    }

    /*
     * ============================= Console starts =============================
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
                    removeBook();
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
        scanner.nextLine();  // Consume newline

        System.out.println("\n========= Details Book entered : ========== \n");
        System.out.println("Book Title: " + title);
        System.out.println("Book Author: " + author);
        System.out.println("Book Genre: " + genre);
        System.out.println("Book ISBN: " + isbn);
        System.out.println("Book Year: " + year);

        try {
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
            book.setISBN(isbn);
            book.setPublicationYear(year);
            bookService.addBook(book);  // Only use bookService now
            System.out.println("Book added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

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

    private void updateBook() {
        System.out.print("Enter the ISBN of the book to update: ");
        String isbn = scanner.nextLine();

        Book bookToUpdate = bookService.getBookByISBN(isbn);

        if (bookToUpdate == null) {
            System.out.println("Error: No book found with ISBN " + isbn);
            return;
        }

        System.out.println("Book found: ");
        System.out.println(bookToUpdate);

        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.println("3. Genre");
        System.out.println("4. Publication Year");
        System.out.println("5. Cancel");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character left by nextInt()

        boolean isUpdated = false;

        switch (choice) {
            case 1:
                System.out.print("Enter new title: ");
                String newTitle = scanner.nextLine();
                try {
                    bookToUpdate.setTitle(newTitle);
                    isUpdated = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                System.out.print("Enter new author: ");
                String newAuthor = scanner.nextLine();
                try {
                    bookToUpdate.setAuthor(newAuthor);
                    isUpdated = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                System.out.print("Enter new genre: ");
                String newGenre = scanner.nextLine();
                try {
                    bookToUpdate.setGenre(newGenre);
                    isUpdated = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                System.out.print("Enter new publication year: ");
                int newYear = scanner.nextInt();
                scanner.nextLine(); // Consume newline character left by nextInt()
                try {
                    bookToUpdate.setPublicationYear(newYear);
                    isUpdated = true;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 5:
                System.out.println("Update canceled.");
                return;
            default:
                System.out.println("Invalid choice. Update canceled.");
                return;
        }

        if (isUpdated) {
            bookService.updateBook(bookToUpdate);
            System.out.println("Book updated successfully.");
        }
    }

    private void removeBook() {
        System.out.print("Enter the ISBN of the book to remove: ");
        String isbn = scanner.nextLine();

        bookService.removeBookByISBN(isbn);
    }

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

    public void sortBooks() {
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

        List<Book> books = bookService.getAllBooks();  // Replace libraryService.getBooks()

        if (books == null || books.isEmpty()) {
            System.out.println("No books available to sort.");
            return;
        }

    }
}
