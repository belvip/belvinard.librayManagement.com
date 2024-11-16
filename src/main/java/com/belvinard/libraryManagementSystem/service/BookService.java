package com.belvinard.libraryManagementSystem.service;

import com.belvinard.libraryManagementSystem.data.LibraryData;
import com.belvinard.libraryManagementSystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final LibraryData libraryData;
    private List<Book> bookCollection;  // Pas besoin de null-check, initialisé dans le constructeur

    @Autowired
    public BookService(LibraryData libraryData) {
        this.libraryData = libraryData;
        this.bookCollection = new ArrayList<>();  // Initialisation ici
    }

    // Méthode pour ajouter un livre
    public void addBook(Book book) {
        if (book != null) {
            bookCollection.add(book);  // Ajouter le livre à la collection
        } else {
            throw new IllegalArgumentException("Book cannot be null.");
        }
    }

    // Lister tous les livres
    public List<Book> listBooks() {
        return bookCollection;
    }

    // Méthode pour obtenir la liste de tous les livres
    public List<Book> getAllBooks() {
        return bookCollection;
    }

    // Mettre à jour un livre
    public void updateBook(Book updatedBook) {
        Book existingBook = getBookByISBN(updatedBook.getISBN());
        if (existingBook != null) {
            // Remplacer le livre existant par le livre mis à jour
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            existingBook.setGenre(updatedBook.getGenre());
            existingBook.setPublicationYear(updatedBook.getPublicationYear());
            existingBook.setDescription(updatedBook.getDescription());
        } else {
            throw new IllegalArgumentException("Book with ISBN " + updatedBook.getISBN() + " not found.");
        }
    }

    // Récupérer un livre par ISBN
    public Book getBookByISBN(String isbn) {
        return libraryData.getBookCollection().values().stream()
                .filter(book -> book.getISBN().equals(isbn))
                .findFirst()
                .orElse(null);  // Retourne null si aucun livre n'est trouvé
    }

    // Supprimer un livre par ISBN
    public void removeBookByISBN(String isbn) {
        boolean removed = libraryData.removeBook(isbn);
        if (!removed) {
            throw new IllegalArgumentException("No book found with ISBN " + isbn);
        } else {
            System.out.println("Book with ISBN " + isbn + " has been removed successfully.");
        }
    }

    // Rechercher des livres (par titre, auteur, ou ISBN)
    public List<Book> searchBooks(String query, String searchType) {
        List<Book> foundBooks = new ArrayList<>();
        Map<String, Book> booksMap = libraryData.getBookCollection();

        booksMap.values().stream()
                .filter(book -> matchBook(book, query, searchType))
                .forEach(foundBooks::add);

        return foundBooks;
    }

    // Méthode auxiliaire pour correspondre aux critères de recherche
    private boolean matchBook(Book book, String query, String searchType) {
        switch (searchType.toLowerCase()) {
            case "title":
                return book.getTitle().toLowerCase().contains(query.toLowerCase());
            case "author":
                return book.getAuthor().toLowerCase().contains(query.toLowerCase());
            case "isbn":
                return book.getISBN().equals(query);
            default:
                throw new IllegalArgumentException("Invalid search type: " + searchType);
        }
    }

    // Recherche binaire des livres
    public Book binarySearchBooks(String query, String searchType) {
        List<Book> books = new ArrayList<>(libraryData.getBookCollection().values());
        Comparator<Book> comparator = getComparatorForSearchType(searchType);

        if (comparator != null) {
            books.sort(comparator);
            return binarySearch(query, books, comparator);
        } else {
            throw new IllegalArgumentException("Invalid search type.");
        }
    }

    // Comparateur en fonction du type de recherche
    private Comparator<Book> getComparatorForSearchType(String searchType) {
        switch (searchType.toLowerCase()) {
            case "title":
                return Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER);
            case "author":
                return Comparator.comparing(Book::getAuthor, String.CASE_INSENSITIVE_ORDER);
            case "isbn":
                return Comparator.comparing(Book::getISBN);
            default:
                return null;
        }
    }

    // Méthode de recherche binaire
    private Book binarySearch(String query, List<Book> books, Comparator<Book> comparator) {
        int low = 0, high = books.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Book midBook = books.get(mid);

            // Comparer le livre au critère de recherche
            int comparison = comparator.compare(midBook, new Book(query, "", "", "", 0, ""));

            if (comparison == 0) {
                return midBook;  // Trouvé
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;  // Aucun livre trouvé
    }

    // Trier les livres par titre
    public void sortBooks() {
        List<Book> books = listBooks();
        books.sort(Comparator.comparing(Book::getTitle));  // Trie les livres par titre
    }
}
