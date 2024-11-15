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
    private String title;
    private String author;
    private String genre;
    private String ISBN;
    private int publicationYear;

    // Allowed genres for validation
    private static final Set<String> ALLOWED_GENRES = new HashSet<>(Set.of(
            "Front-end Development", "Web Design", "Software Development", "Full-stack Development", "Back-end Development"
    ));

    @Override
    public String toString() {
        return String.format("Book Title --> %s%nBook Author --> %s%nBook Genre --> %s%nBook ISBN --> %s%nBook Publication Year --> %d%n",
                title, author, genre, ISBN, publicationYear);
    }

    // Setters with validation

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty() || title.length() < 3) {
            throw new IllegalArgumentException("Invalid title. It must be at least 3 characters long and not empty.");
        }
        if (!Pattern.matches("^[a-zA-Z0-9\\s]+$", title)) {
            throw new IllegalArgumentException("Invalid title format. Only alphabetic characters, numbers, and spaces are allowed.");
        }
        this.title = title;
    }

    public void setAuthor(String author) {
        if (author == null || !Pattern.matches("^[a-zA-Z]+([\\s][a-zA-Z]+)*$", author)) {
            throw new IllegalArgumentException("Invalid author format. Only alphabetic characters and spaces are allowed.");
        }
        this.author = author;
    }

    public void setISBN(String ISBN) {
        if (ISBN == null || !Pattern.matches("\\d{5}", ISBN)) {
            throw new IllegalArgumentException("Invalid ISBN format. Must be 5 digits.");
        }
        this.ISBN = ISBN;
    }

    public void setPublicationYear(int publicationYear) {
        int currentYear = java.time.Year.now().getValue();
        if (publicationYear < 1000 || publicationYear > currentYear) {
            throw new IllegalArgumentException("Invalid publication year. It must be between 1000 and the current year.");
        }
        this.publicationYear = publicationYear;
    }

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
