package com.belvinard.libraryManagementSystem.console;

import com.belvinard.libraryManagementSystem.model.Book;
import com.belvinard.libraryManagementSystem.service.BookService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Data
@AllArgsConstructor
public class ConsoleHandler {
    private final BookService bookService;
    private final Scanner scanner;

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
                    running = false;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
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
                        "Press 4 for Exiting the portal\n"
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
        scanner.nextLine();  // Consume newline

        // Debugging output to see the values entered
        System.out.println("\n========= Details Book entered : ========== \n");
        System.out.println("Book Title: " + title);
        System.out.println("Book Author: " + author);
        System.out.println("Book Genre: " + genre);
        System.out.println("Book ISBN: " + isbn);
        System.out.println("Book Year: " + year);

        try {
            Book book = new Book();
            book.setTitle(title);  // Use setter to validate
            book.setAuthor(author);  // Use setter to validate
            book.setGenre(genre);  // Use setter to validate
            book.setISBN(isbn);  // Use setter to validate
            book.setPublicationYear(year);  // Use setter to validate
            // Book book = new Book(title, author, genre, isbn, year);  // Calls the constructor which enforces validation
            bookService.addBook(book);
            System.out.println("Book added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());  // This will catch the validation exception
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
            return -1;  // Return an invalid choice to prompt re-entry
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
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter new title: ");
                String newTitle = scanner.nextLine();
                try {
                    bookToUpdate.setTitle(newTitle);
                    System.out.println("Title updated successfully.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                System.out.print("Enter new author: ");
                String newAuthor = scanner.nextLine();
                try {
                    bookToUpdate.setAuthor(newAuthor);
                    System.out.println("Author updated successfully.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 3:
                System.out.print("Enter new genre: ");
                String newGenre = scanner.nextLine();
                try {
                    bookToUpdate.setGenre(newGenre);
                    System.out.println("Genre updated successfully.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 4:
                System.out.print("Enter new publication year: ");
                int newYear = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                try {
                    bookToUpdate.setPublicationYear(newYear);
                    System.out.println("Publication year updated successfully.");
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

        // Update the book in the service
        bookService.updateBook(bookToUpdate);
        System.out.println("Book updated successfully.");
    }


    /*
    * ============================= Update book starts =============================
    */




}
