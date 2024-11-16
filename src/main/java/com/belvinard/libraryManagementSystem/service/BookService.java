package com.belvinard.libraryManagementSystem.service;

import com.belvinard.libraryManagementSystem.data.LibraryData;
import com.belvinard.libraryManagementSystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    private final LibraryData libraryData;
    //private List<Book> bookCollection;
    // private Map<String, Book> bookCollection;
    private Map<String, Book> bookCollection = new HashMap<>();
// Pas besoin de null-check, initialisé dans le constructeur

    @Autowired
    public BookService(LibraryData libraryData) {
        this.libraryData = libraryData;
        this.bookCollection = new HashMap<>();
        // this.bookCollection = new ArrayList<>();  // Initialisation ici
    }

    // Méthode pour ajouter un livre
    public void addBook(Book book) {
        // Vérification si l'ISBN existe déjà dans la collection
        if (bookCollection.containsKey(book.getISBN())) {
            throw new IllegalArgumentException("A book with ISBN " + book.getISBN() + " already exists.");
        }
        // Ajouter le livre à la collection en utilisant son ISBN comme clé
        bookCollection.put(book.getISBN(), book);
    }

    // Lister tous les livres
    public List<Book> listBooks() {
        // Convertir la Map en une List pour l'affichage
        return new ArrayList<>(bookCollection.values());
    }


    // Méthode pour obtenir la liste de tous les livres
    public List<Book> getAllBooks() {
        // Convertir les livres de la Map en une List
        return new ArrayList<>(bookCollection.values());
    }

    // Mettre à jour un livre
    /*public void updateBook(Book updatedBook) {
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
    } */

    // Méthode pour obtenir un livre par ISBN
    public Book getBookByISBN(String isbn) {
        return bookCollection.get(isbn);  // Retourne le livre associé à l'ISBN
    }

    // Méthode pour mettre à jour un livre
    public void updateBook(Book updatedBook) {
        if (!bookCollection.containsKey(updatedBook.getISBN())) {
            throw new IllegalArgumentException("No book found with ISBN " + updatedBook.getISBN());
        }
        bookCollection.put(updatedBook.getISBN(), updatedBook);  // Met à jour le livre dans le Map
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
