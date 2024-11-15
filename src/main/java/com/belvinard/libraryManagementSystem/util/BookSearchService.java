package com.belvinard.libraryManagementSystem.util;

import com.belvinard.libraryManagementSystem.model.Book;

import java.util.*;

public class BookSearchService {

    // Linear Search: Search for a book by title
    public static List<Book> linearSearchByTitle(List<Book> books, String title) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                foundBooks.add(book);
            }
        }
        return foundBooks; // Return list of matching books
    }

    // Linear Search: Search for a book by author
    public static List<Book> linearSearchByAuthor(List<Book> books, String author) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                foundBooks.add(book);
            }
        }
        return foundBooks; // Return list of matching books
    }

    // Linear Search: Search for a book by genre
    public static List<Book> linearSearchByGenre(List<Book> books, String genre) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) {
                foundBooks.add(book);
            }
        }
        return foundBooks; // Return list of matching books
    }

    // Linear Search: Search for a book by publication year
    public static List<Book> linearSearchByPublicationYear(List<Book> books, int year) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublicationYear() == year) {
                foundBooks.add(book);
            }
        }
        return foundBooks; // Return list of matching books
    }

    // Binary Search: Search for a book by ISBN
    public static Book binarySearchByISBN(List<Book> books, String isbn) {
        books.sort(Comparator.comparing(Book::getISBN)); // Sort by ISBN
        int low = 0, high = books.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Book midBook = books.get(mid);

            int comparison = midBook.getISBN().compareTo(isbn); // Use getISBN() instead of getIsbn()
            if (comparison == 0) {
                return midBook;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }



    // Binary Search: Search for a book by title
    public static Book binarySearchByTitle(List<Book> books, String title) {
        books.sort(Comparator.comparing(Book::getTitle)); // Sort by title
        int low = 0, high = books.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Book midBook = books.get(mid);

            int comparison = midBook.getTitle().compareToIgnoreCase(title);
            if (comparison == 0) {
                return midBook;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    // Binary Search: Search for a book by author
    public static Book binarySearchByAuthor(List<Book> books, String author) {
        books.sort(Comparator.comparing(Book::getAuthor)); // Sort by author
        int low = 0, high = books.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Book midBook = books.get(mid);

            int comparison = midBook.getAuthor().compareToIgnoreCase(author);
            if (comparison == 0) {
                return midBook;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    // Binary Search: Search for a book by genre
    public static Book binarySearchByGenre(List<Book> books, String genre) {
        books.sort(Comparator.comparing(Book::getGenre)); // Sort by genre
        int low = 0, high = books.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Book midBook = books.get(mid);

            int comparison = midBook.getGenre().compareToIgnoreCase(genre);
            if (comparison == 0) {
                return midBook;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    // Binary Search: Search for a book by publication year
    public static Book binarySearchByPublicationYear(List<Book> books, int year) {
        books.sort(Comparator.comparingInt(Book::getPublicationYear)); // Sort by publication year
        int low = 0, high = books.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Book midBook = books.get(mid);

            if (midBook.getPublicationYear() == year) {
                return midBook;
            } else if (midBook.getPublicationYear() < year) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}
