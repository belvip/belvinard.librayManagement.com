package com.belvinard.libraryManagementSystem;

import com.belvinard.libraryManagementSystem.console.ConsoleHandler;
import com.belvinard.libraryManagementSystem.data.LibraryData;
import com.belvinard.libraryManagementSystem.service.BookService;
import com.belvinard.libraryManagementSystem.service.LibraryService;

import java.util.Scanner;

/**
 * The main entry point of the Library Management System application.
 * This class initializes necessary components and starts the user interaction.
 */
public class LibraryManagementSystemApplication {

    public static void main(String[] args) {
        LibraryData libraryData = new LibraryData();
        BookService bookService = new BookService(libraryData);

        // Create a Scanner instance
        Scanner scanner = new Scanner(System.in);

        // Pass both BookService and Scanner to the ConsoleHandler constructor
        ConsoleHandler consoleHandler = new ConsoleHandler(bookService, scanner);
        consoleHandler.start();

        // Close the scanner when the application exits
        scanner.close();
    }
}
