package com.belvinard.libraryManagementSystem.console;

import com.belvinard.libraryManagementSystem.model.Book;
import com.belvinard.libraryManagementSystem.service.BookService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.InputMismatchException;
import java.util.Scanner;

@Data
@AllArgsConstructor
public class ConsoleHandler {
    private final BookService bookService;
    private final Scanner scanner;

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
                    running = false;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Displays the main menu for the library system.
     */
    private void displayMenu() {
        System.out.println("\n----- The Library Management System Portal -----");
        System.out.println(
                "\nPress 1 for Adding Book\n" +
                        "Press 2 for Exiting the portal\n"
        );
        System.out.print("Enter your choice: ");
    }

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

        Book book = new Book(title, author, genre, isbn, year);

        try {
            bookService.addBook(book);
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
            return -1;  // Return an invalid choice to prompt re-entry
        }
    }
}
