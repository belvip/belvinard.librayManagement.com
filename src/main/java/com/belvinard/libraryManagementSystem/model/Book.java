package com.belvinard.libraryManagementSystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.HashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    // Book attributes
    private String title;
    private String author;
    private String genre;
    private String ISBN;
    private int publicationYear;

    // Allowed genres for validation
    private static final Set<String> ALLOWED_GENRES = new HashSet<>(Set.of(
            "Front-end Development", "Web Design", "Software Development", "Full-stack Development", "Back-end Development"
    ));

    /**
     * Custom toString() method for readable display of book information.
     * Uses String.format() for clean formatting.
     */
    @Override
    public String toString() {
        return String.format("Book Title --> %s%nBook Author --> %s%nBook Genre --> %s%nBook ISBN --> %s%nBook Publication Year --> %d%n",
                title, author, genre, ISBN, publicationYear);
    }

    /**
     * Sets the book title with validation.
     * Ensures title is not null, not empty, at least 3 characters long, and
     * only contains alphabetic characters, numbers, and spaces.
     *
     * @param title the title of the book
     */
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty() || title.length() < 3) {
            throw new IllegalArgumentException("Invalid title. It must be at least 3 characters long and not empty.");
        }
        if (!Pattern.matches("^[a-zA-Z0-9\\s]+$", title)) {
            throw new IllegalArgumentException("Invalid title format. Only alphabetic characters, numbers, and spaces are allowed.");
        }
        this.title = title;
    }

    /**
     * Sets the book author with validation.
     * Ensures author name is not null and contains only alphabetic characters and spaces.
     *
     * @param author the author of the book
     */
    public void setAuthor(String author) {
        if (author == null || !Pattern.matches("^[a-zA-Z]+([\\s][a-zA-Z]+)*$", author)) {
            throw new IllegalArgumentException("Invalid author format. Only alphabetic characters and spaces are allowed.");
        }
        this.author = author;
    }

    /**
     * Sets the book ISBN with validation.
     * Ensures ISBN is exactly 5 digits long.
     *
     * @param ISBN the ISBN number of the book
     */
    public void setISBN(String ISBN) {
        if (ISBN == null || !Pattern.matches("\\d{5}", ISBN)) {
            throw new IllegalArgumentException("Invalid ISBN format. Must be 5 digits.");
        }
        this.ISBN = ISBN;
    }

    /**
     * Sets the publication year of the book with validation.
     * Ensures the publication year is between 1000 and the current year.
     *
     * @param publicationYear the year the book was published
     */
    public void setPublicationYear(int publicationYear) {
        int currentYear = java.time.Year.now().getValue();
        if (publicationYear < 1000 || publicationYear > currentYear) {
            throw new IllegalArgumentException("Invalid publication year. It must be between 1000 and the current year.");
        }
        this.publicationYear = publicationYear;
    }

    /**
     * Sets the book genre with validation.
     * Ensures the genre is not null and matches one of the allowed genres (case-insensitive).
     *
     * @param genre the genre of the book
     */
    public void setGenre(String genre) {
        if (genre == null || ALLOWED_GENRES.stream()
                .noneMatch(allowedGenre -> allowedGenre.equalsIgnoreCase(genre))) {
            throw new IllegalArgumentException(
                    "Invalid genre. It must be one of: " + String.join(", ", ALLOWED_GENRES)
            );
        }
        this.genre = genre;
    }
}
