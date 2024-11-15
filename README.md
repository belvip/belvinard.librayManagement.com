# Library Management System 📚

Welcome to the Library Management System! This simple Java application allows you to manage books in a library, including adding new books to the system.

## Table of Contents 📝

1. [Introduction](#introduction)
2. [Technologies Used](#technologies-used)
3. [How to Run](#how-to-run)
4. [System Overview](#system-overview)
   - [Packages & Classes](#packages--classes)
5. [Step 1: Add a Book 📖](#step-1-add-a-book-)
6. [Conclusion](#conclusion)

---

## Introduction 🤖

This application helps you manage books in a library with a focus on adding new books to the library database.

### Features
- Add a book to the library
- Store book details such as title, author, genre, ISBN, and publication year

---

## Technologies Used 🛠️

- **Java 8+** for development
- **Spring Framework** for dependency injection and managing beans
- **Scanner** for user input handling in the console

---

## How to Run 🏃

Follow these steps to set up and run the Library Management System project on your local machine:

### 1. Clone the Repository

First, clone the project to your local machine:

```bash
   git clone https://github.com/belvip/Belvi.com.libraryManagementSystem.git
```

*This will download all the project files to a folder named LibraryManagementSystem.*

### 📂 Build the project using Maven:

Navigate to the project’s root directory, then use Maven to build the project:

```bash
   cd LibraryManagementSystem
   mvn clean install

```

*This command will launch the console-based interface, allowing you to interact with the library system directly in your terminal.*

### 📚 License:

This project is licensed under the MIT License - see the LICENSE file for more details.

---

## System Overview 🌍

This application is structured in multiple packages, each with a specific role to ensure that the system is modular, maintainable, and scalable.

## Packages & Classes 📦

### `com.belvinard.libraryManagementSystem.model` - **Book Class**
- **Description**: This package contains the `Book` class, which represents the entity for books in the library system.
- **Attributes**: `title`, `author`, `ISBN`, `publicationYear`, `genre`
- **Validation**: Handles input validation, such as:
  - Ensuring the book title is at least 3 characters long.
  - Verifying that the `ISBN` is a 5-digit number.
  - Checking that the author's name contains only alphabetic characters.
- **Validation Example**: 
  - The title must have at least 3 characters.
  - The genre must be one of the predefined values (e.g., "Fiction", "Non-Fiction").

### `com.belvinard.libraryManagementSystem.data` - **LibraryData Class**
- **Description**: This package handles the collection of books. The `LibraryData` class stores a list of books and provides methods for managing the collection.
- **Functionality**: Adds books to the internal list, `bookCollection`.

### `com.belvinard.libraryManagementSystem.service` - **BookService Class**
- **Description**: This package contains the `BookService` class, which interacts with the data layer (`LibraryData`).
- **Functionality**: The service layer ensures separation of business logic (e.g., adding books) from the presentation layer.
- **Dependencies**: `LibraryData` is injected into `BookService` to handle book management.

### `com.belvinard.libraryManagementSystem.config` - **LibraryConfig Class**
- **Description**: This package sets up the Spring configuration, defining beans for `LibraryData` and `BookService`, making them available for dependency injection.
- **Functionality**: Uses `@Bean` annotations to define application configuration and manage dependencies between classes.

### `com.belvinard.libraryManagementSystem.console` - **ConsoleHandler Class**
- **Description**: This package contains the `ConsoleHandler` class, which provides user interaction via the console.
- **Functionality**: Displays the menu, takes user input, and calls relevant methods in `BookService` to perform actions like adding books.

### `com.belvinard.libraryManagementSystem` - **LibraryManagementSystemApplication Class**
- **Description**: The main entry point for the application, initializing the system and starting the console interface for user interaction.

---

# Step 1: Add a Book 📖

The first feature of the system allows you to add a new book to the library. Follow the steps below:

### 🚀 Launch the Application
Run the `LibraryManagementSystemApplication` class to start the application.

### 📋 Console Menu
Once the application starts, you will see the following menu in the console:

================= Library Management System =================

- Add Book
- Exit
- Enter your choice:


### 📘 Add a Book
Select option **1** to add a book. You will be prompted to enter the following details:

- **Book Title**: Enter the title of the book (must be at least 3 characters long).
- **Author**: Enter the author’s name (only alphabetic characters are allowed).
- **Genre**: Enter the genre (must be one of the predefined values like "Fiction", "Non-Fiction", etc.).
- **ISBN**: Enter the ISBN (must be exactly 5 digits).
- **Publication Year**: Enter the publication year (must be between 1000 and the current year).

### ✅ Successful Addition
After entering the details, the book will be added to the system. If any input is invalid, an error message will be displayed, and the user will be prompted to try again.

---

