package com.belvinard.libraryManagementSystem.util;

import com.belvinard.libraryManagementSystem.model.Book;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.*;
import java.util.stream.Collectors;

public class BookSearchService {

    // Index inversé pour rechercher des livres par titre, auteur, genre, etc.
    private static Map<String, List<Book>> titleIndex = new HashMap<>();
    private static Map<String, List<Book>> authorIndex = new HashMap<>();
    private static Map<String, List<Book>> genreIndex = new HashMap<>();
    private static Map<Integer, List<Book>> yearIndex = new HashMap<>();

    // Indexer les livres par différents critères
    public static void indexBooks(List<Book> books) {
        for (Book book : books) {
            // Indexer par titre
            titleIndex.computeIfAbsent(book.getTitle().toLowerCase(), k -> new ArrayList<>()).add(book);
            // Indexer par auteur
            authorIndex.computeIfAbsent(book.getAuthor().toLowerCase(), k -> new ArrayList<>()).add(book);
            // Indexer par genre
            genreIndex.computeIfAbsent(book.getGenre().toLowerCase(), k -> new ArrayList<>()).add(book);
            // Indexer par année de publication
            yearIndex.computeIfAbsent(book.getPublicationYear(), k -> new ArrayList<>()).add(book);
        }
    }

    // Recherche par titre en utilisant l'index inversé
    public static List<Book> searchByTitle(String title) {
        return titleIndex.getOrDefault(title.toLowerCase(), Collections.emptyList());
    }

    // Recherche par auteur en utilisant l'index inversé
    public static List<Book> searchByAuthor(String author) {
        return authorIndex.getOrDefault(author.toLowerCase(), Collections.emptyList());
    }

    // Recherche par genre en utilisant l'index inversé
    public static List<Book> searchByGenre(String genre) {
        return genreIndex.getOrDefault(genre.toLowerCase(), Collections.emptyList());
    }

    // Recherche par année en utilisant l'index inversé
    public static List<Book> searchByPublicationYear(int year) {
        return yearIndex.getOrDefault(year, Collections.emptyList());
    }

    // Recherche multi-critères : titre, auteur, genre, année
    public static List<Book> searchBooksByMultipleCriteria(String title, String author, String genre, Integer year, List<Book> books) {
        return books.stream()
                .filter(book -> (title == null || book.getTitle().toLowerCase().contains(title.toLowerCase())) &&
                        (author == null || book.getAuthor().toLowerCase().contains(author.toLowerCase())) &&
                        (genre == null || book.getGenre().toLowerCase().contains(genre.toLowerCase())) &&
                        (year == null || book.getPublicationYear() == year))
                .collect(Collectors.toList());
    }

    // Recherche par mots-clés (partie du titre ou description)
    public static List<Book> searchByKeywords(String keywords, List<Book> books) {
        String lowerCaseKeywords = keywords.toLowerCase();
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(lowerCaseKeywords) ||
                        book.getDescription().toLowerCase().contains(lowerCaseKeywords))
                .collect(Collectors.toList());
    }

    // Recherche floue : recherche par titre avec la distance de Levenshtein
    public static List<Book> searchByFuzzyTitle(String title, List<Book> books) {
        LevenshteinDistance levenshtein = new LevenshteinDistance();
        String lowerCaseTitle = title.toLowerCase();
        return books.stream()
                .filter(book -> levenshtein.apply(book.getTitle().toLowerCase(), lowerCaseTitle) <= 3)
                .collect(Collectors.toList());
    }

    // Recherche floue : recherche par auteur avec la distance de Levenshtein
    public static List<Book> searchByFuzzyAuthor(String author, List<Book> books) {
        LevenshteinDistance levenshtein = new LevenshteinDistance();
        String lowerCaseAuthor = author.toLowerCase();
        return books.stream()
                .filter(book -> levenshtein.apply(book.getAuthor().toLowerCase(), lowerCaseAuthor) <= 3)
                .collect(Collectors.toList());
    }

    // Pagination des résultats : méthode de recherche par titre avec pagination
    public static List<Book> searchByTitleWithPagination(String title, List<Book> books, int pageNumber, int pageSize) {
        List<Book> matchingBooks = searchByTitle(title);  // Recherche des livres par titre
        int start = (pageNumber - 1) * pageSize;
        int end = Math.min(start + pageSize, matchingBooks.size());

        return matchingBooks.subList(start, end);  // Retourne la sous-liste pour la pagination
    }

    // Pagination des résultats : méthode de recherche par auteur avec pagination
    public static List<Book> searchByAuthorWithPagination(String author, List<Book> books, int pageNumber, int pageSize) {
        List<Book> matchingBooks = searchByAuthor(author);  // Recherche des livres par auteur
        int start = (pageNumber - 1) * pageSize;
        int end = Math.min(start + pageSize, matchingBooks.size());

        return matchingBooks.subList(start, end);  // Retourne la sous-liste pour la pagination
    }

    // Pagination des résultats : méthode de recherche par genre avec pagination
    public static List<Book> searchByGenreWithPagination(String genre, List<Book> books, int pageNumber, int pageSize) {
        List<Book> matchingBooks = searchByGenre(genre);  // Recherche des livres par genre
        int start = (pageNumber - 1) * pageSize;
        int end = Math.min(start + pageSize, matchingBooks.size());

        return matchingBooks.subList(start, end);  // Retourne la sous-liste pour la pagination
    }

    // Pagination des résultats : méthode de recherche par année de publication avec pagination
    public static List<Book> searchByPublicationYearWithPagination(int year, List<Book> books, int pageNumber, int pageSize) {
        List<Book> matchingBooks = searchByPublicationYear(year);  // Recherche des livres par année
        int start = (pageNumber - 1) * pageSize;
        int end = Math.min(start + pageSize, matchingBooks.size());

        return matchingBooks.subList(start, end);  // Retourne la sous-liste pour la pagination
    }

    // Recherche binaire : recherche par ISBN (optimisée pour les grandes listes)
    public static Book binarySearchByISBN(List<Book> books, String isbn) {
        books.sort(Comparator.comparing(Book::getISBN));  // Trie la liste des livres par ISBN
        int low = 0, high = books.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Book midBook = books.get(mid);

            int comparison = midBook.getISBN().compareTo(isbn);
            if (comparison == 0) {
                return midBook;  // Livre trouvé
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;  // Aucun livre trouvé
    }

    // Recherche binaire : recherche par titre
    public static Book binarySearchByTitle(List<Book> books, String title) {
        books.sort(Comparator.comparing(Book::getTitle));  // Trie la liste des livres par titre
        int low = 0, high = books.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Book midBook = books.get(mid);

            int comparison = midBook.getTitle().compareToIgnoreCase(title);
            if (comparison == 0) {
                return midBook;  // Livre trouvé
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;  // Aucun livre trouvé
    }
}
