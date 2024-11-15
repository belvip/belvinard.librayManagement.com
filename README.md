### Skills Learned in the First Stage for Adding a Book

- 🔹 **Basic Java Object Creation**: Learned to create Java objects with attributes, including a constructor and parameterized constructors.
- 🔹 **Input Validation**: Developed custom setters with validation checks for attributes like `title`, `author`, `ISBN`, `genre`, and `publicationYear`.
- 🔹 **Exception Handling**: Implemented `IllegalArgumentException` for input errors, including meaningful error messages for each case.
- 🔹 **Using Regular Expressions**: Leveraged regular expressions (`Pattern.matches`) to validate text inputs, such as titles, authors, and ISBNs.
- 🔹 **Implementing Business Rules**: Enforced business rules (e.g., disallow duplicate ISBNs, validate specific genres).
- 🔹 **Console-Based User Interaction**: Built a simple, user-friendly console interface with input prompts and feedback messages.
- 🔹 **Data Storage with Collections**: Utilized `ArrayList` to store book objects in memory.
- 🔹 **Formatted Output with `toString()`**: Customized the `toString()` method to display book details in a readable format.
- 🔹 **Looping and Conditionals for Menu Navigation**: Used `while` loops and `switch` cases to manage user choices and keep the application running until the user exits.
- 🔹 **Error Handling for Duplicate Entries**: Created logic to check for existing ISBNs before adding a book and display appropriate error messages.

---
# Skills Learned During the "Display All Books" Stage 📚

## General Programming Skills
- ✅ **Creating and Using Getter Methods**: Learned to expose private fields safely using public getter methods.
- ✅ **Iterating Over Collections**: Practiced iterating over a list of objects (`List<Book>`) to display each item.
- ✅ **Testing Application Features**: Verified the functionality of methods through manual testing and debugging.

## Java-Specific Skills
- 🛠 **Working with Java Collections**: Used `ArrayList` and `List` to store and retrieve objects.
- 🛠 **Implementing Methods in Classes**: Added specific methods in the `LibraryData` and `BookService` classes to support functionality.
- 🛠 **Encapsulation and Data Access**: Applied the principle of encapsulation to protect data and expose it through well-defined interfaces.

## Spring Framework Skills
- 🌱 **Defining Components**: Utilized `@Component` to define Spring-managed beans for dependency injection.
- 🌱 **Managing Dependencies**: Connected `LibraryData` and `BookService` components in a structured manner.

## Debugging and Problem-Solving Skills
- 🐛 **Resolving "Cannot Find Symbol" Errors**: Identified and fixed missing method definitions by adding the required getter.
- 🐛 **Refining Logic**: Adjusted the code to correctly retrieve and display stored books.
- 🐛 **Testing Edge Cases**: Ensured that no duplicate books with the same ISBN were displayed.

## Console-Based UI Skills
- 💻 **Designing User Menus**: Created a user-friendly console menu to display book options.
- 💻 **Output Formatting**: Enhanced readability of book details using `toString()` formatting in the `Book` class.

## Object-Oriented Programming (OOP) Concepts
- 📦 **Separation of Concerns**: Separated data handling, business logic, and presentation layers into distinct classes.
- 📦 **Reusable Methods**: Designed a reusable `displayAllBooks` method in `ConsoleHandler`.

## Communication and Validation
- 📢 **Meaningful Output Messages**: Displayed clear messages for users, such as success notifications and book details.
- 📢 **Edge Case Handling**: Ensured that the application gracefully handled invalid data.

---

# Skills Learned During the "Update Book" Stage 📚

## 🛠️ Technical Skills
- **Java Method Design**:
  - Creating methods like `updateBook` and `getBookByISBN` to manage specific functionality.
- **Java Streams**:
  - Using streams to filter and find specific data within a collection.
  - Employing methods like `filter()`, `findFirst()`, and `orElse()`.
- **Exception Handling**:
  - Throwing and handling `IllegalArgumentException` for invalid or not-found cases.
- **Looping Through Collections**:
  - Iterating through lists to find and replace specific elements.
- **Setters with Validation**:
  - Using setter methods in the `Book` class to ensure updates adhere to business rules.

## 🖥️ Practical Console UI Skills
- **User Interaction**:
  - Prompting the user for input to choose and specify what to update.
- **Input Validation**:
  - Verifying the correctness of user input and providing feedback for invalid entries.
- **Update Options**:
  - Implementing a flexible menu-driven system to allow updates for individual book attributes.

## 🧹 Code Quality and Maintenance
- **Clean Code Practices**:
  - Structuring code for readability and reusability.
  - Adding meaningful comments for better understanding.
- **Flag Implementation**:
  - Using flags like `isUpdated` to track if changes were successfully made.
- **Error Messaging**:
  - Customizing error messages to provide clear and actionable feedback.

## 🧠 Conceptual Knowledge
- **Object-Oriented Programming (OOP)**:
  - Leveraging encapsulation to modify specific attributes of a book.
- **Business Logic Separation**:
  - Delegating update operations to the service layer (`BookService`) while keeping the console layer focused on user interaction.

## 💡 Problem-Solving Strategies
- **Handling Edge Cases**:
  - Accounting for invalid or no input scenarios during updates.
  - Ensuring that update operations do not proceed for invalid data.

By mastering these skills, you've built a robust and user-friendly update functionality in your library management system! 🎉





Please list the various skills learned during this stage for updating book in markdown format wit emojolis