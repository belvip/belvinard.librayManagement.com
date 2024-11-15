package com.belvinard.libraryManagementSystem.util;

import com.belvinard.libraryManagementSystem.model.Book;

import java.util.List;

public class BookSortService {

    private List<Book> books;


    public BookSortService(List<Book> books) {
        this.books = books;
    }


    /*
     * ============================= Bubble Sort method book starts =============================
     */

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
    // Bubble Sort: Sort books by title in ascending order
    /*public static void bubbleSortByTitle(List<Book> books) {
        if (books == null || books.isEmpty()) {
            System.out.println("No books available to sort.");
            return;
        }

        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - 1 - i; j++) {
                if (books.get(j).getTitle().compareTo(books.get(j + 1).getTitle()) > 0) {
                    // Swap books[j] and books[j + 1]
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
    } */

    public static void bubbleSortByTitle(List<Book> books) {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - 1 - i; j++) {
                if (books.get(j).getTitle().compareTo(books.get(j + 1).getTitle()) > 0) {
                    // Swap books[j] and books[j + 1]
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
    }


    // Bubble Sort: Sort books by publication year in ascending order
    public static void bubbleSortByYear(List<Book> books) {
        int n = books.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (books.get(j).getPublicationYear() > books.get(j + 1).getPublicationYear()) {
                    // Swap books if they are in the wrong order
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
    }

    // Bubble Sort: Sort books by author alphabetically
    public static void bubbleSortByAuthor(List<Book> books) {
        int n = books.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (books.get(j).getAuthor().compareTo(books.get(j + 1).getAuthor()) > 0) {
                    // Swap books if they are in the wrong order
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
    }

    public static void bubbleSortByIsbn(List<Book> books) {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - 1 - i; j++) {
                if (books.get(j).getISBN().compareTo(books.get(j + 1).getISBN()) > 0) {
                    // Swap books[j] and books[j + 1]
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
    }



    /*
     * ============================= Bubble Sort method book ends =============================
     */

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

    /*
     * ============================= Selection Sort method book starts =============================
     */

    // Selection Sort: Sort books by publication year in ascending order
    public static void selectionSortByYear(List<Book> books) {
        int n = books.size();
        // Loop to iterate over the unsorted portion of the list
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i; // Assume the current index is the smallest
            // Loop to find the smallest element in the remaining list
            for (int j = i + 1; j < n; j++) {
                if (books.get(j).getPublicationYear() < books.get(minIdx).getPublicationYear()) {
                    minIdx = j; // Update index of smallest element
                }
            }
            // Swap the smallest element with the first element of the unsorted portion
            Book temp = books.get(i);
            books.set(i, books.get(minIdx));
            books.set(minIdx, temp);
        }
    }

    // Selection Sort: Sort books by author alphabetically
    public static void selectionSortByAuthor(List<Book> books) {
        int n = books.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (books.get(j).getAuthor().compareTo(books.get(minIdx).getAuthor()) < 0) {
                    minIdx = j; // Update the index of the smallest element
                }
            }
            // Swap the smallest element with the first element of the unsorted portion
            Book temp = books.get(i);
            books.set(i, books.get(minIdx));
            books.set(minIdx, temp);
        }
    }

    // Selection Sort: Sort books by title alphabetically
    public static void selectionSortByTitle(List<Book> books) {
        int n = books.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (books.get(j).getTitle().compareTo(books.get(minIdx).getTitle()) < 0) {
                    minIdx = j; // Update the index of the smallest element
                }
            }
            // Swap the smallest element with the first element of the unsorted portion
            Book temp = books.get(i);
            books.set(i, books.get(minIdx));
            books.set(minIdx, temp);
        }
    }

    public static void selectionSortByIsbn(List<Book> books) {
        for (int i = 0; i < books.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < books.size(); j++) {
                if (books.get(j).getISBN().compareTo(books.get(minIndex).getISBN()) < 0) {
                    minIndex = j;
                }
            }
            // Swap the minIndex book with the current book
            Book temp = books.get(i);
            books.set(i, books.get(minIndex));
            books.set(minIndex, temp);
        }
    }



    /*
     * ============================= Selection Sort method book ends =============================
     */

    /* ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

    /*
     * ============================= QuickSort Sort method book starts =============================
     */

    // QuickSort: Sort books by author in alphabetical order
    public static void quickSortByAuthor(List<Book> books, int low, int high) {
        if (low < high) {
            // Partition the list and get the pivot index
            int pi = partition(books, low, high);
            // Recursively sort elements before and after the pivot
            quickSortByAuthor(books, low, pi - 1);
            quickSortByAuthor(books, pi + 1, high);
        }
    }

    // Helper method to partition the list for QuickSort by author
    private static int partition(List<Book> books, int low, int high) {
        Book pivot = books.get(high); // Use the last element as the pivot
        int i = low - 1; // Index for the smaller element
        for (int j = low; j < high; j++) {
            // Compare authors lexicographically
            if (books.get(j).getAuthor().compareTo(pivot.getAuthor()) < 0) {
                i++;
                // Swap elements if they are in the wrong order
                Book temp = books.get(i);
                books.set(i, books.get(j));
                books.set(j, temp);
            }
        }
        // Swap the pivot element to its correct position
        Book temp = books.get(i + 1);
        books.set(i + 1, books.get(high));
        books.set(high, temp);
        return i + 1; // Return the index of the pivot
    }


    // QuickSort: Sort books by publication year in ascending order
    public static void quickSortByYear(List<Book> books, int low, int high) {
        if (low < high) {
            // Partition the list and get the pivot index
            int pi = partitionByYear(books, low, high);
            // Recursively sort elements before and after the pivot
            quickSortByYear(books, low, pi - 1);
            quickSortByYear(books, pi + 1, high);
        }
    }

    // Helper method to partition the list for QuickSort by year
    private static int partitionByYear(List<Book> books, int low, int high) {
        Book pivot = books.get(high); // Use the last element as the pivot
        int i = low - 1; // Index for the smaller element
        for (int j = low; j < high; j++) {
            if (books.get(j).getPublicationYear() < pivot.getPublicationYear()) {
                i++;
                // Swap elements if they are in the wrong order
                Book temp = books.get(i);
                books.set(i, books.get(j));
                books.set(j, temp);
            }
        }
        // Swap the pivot element to its correct position
        Book temp = books.get(i + 1);
        books.set(i + 1, books.get(high));
        books.set(high, temp);
        return i + 1; // Return the index of the pivot
    }



    // QuickSort: Sort books by title alphabetically
    public static void quickSortByTitle(List<Book> books, int low, int high) {
        if (low < high) {
            // Partition the list and get the pivot index
            int pi = partitionByTitle(books, low, high);
            // Recursively sort elements before and after the pivot
            quickSortByTitle(books, low, pi - 1);
            quickSortByTitle(books, pi + 1, high);
        }
    }

    // Helper method to partition the list for QuickSort by title
    private static int partitionByTitle(List<Book> books, int low, int high) {
        Book pivot = books.get(high); // Use the last element as the pivot
        int i = low - 1; // Index for the smaller element
        for (int j = low; j < high; j++) {
            if (books.get(j).getTitle().compareTo(pivot.getTitle()) < 0) {
                i++;
                // Swap elements if they are in the wrong order
                Book temp = books.get(i);
                books.set(i, books.get(j));
                books.set(j, temp);
            }
        }
        // Swap the pivot element to its correct position
        Book temp = books.get(i + 1);
        books.set(i + 1, books.get(high));
        books.set(high, temp);
        return i + 1; // Return the index of the pivot
    }

    public static void quickSortByGenre(List<Book> books, int low, int high) {
        if (low < high) {
            int pi = partitionByGenre(books, low, high); // Partition index
            quickSortByGenre(books, low, pi - 1);        // Recursively sort left subarray
            quickSortByGenre(books, pi + 1, high);      // Recursively sort right subarray
        }
    }

    private static int partitionByGenre(List<Book> books, int low, int high) {
        Book pivot = books.get(high); // Use the last element as pivot
        int i = (low - 1);            // Pointer for smaller element

        for (int j = low; j < high; j++) {
            // Compare genres lexicographically
            if (books.get(j).getGenre().compareTo(pivot.getGenre()) < 0) {
                i++;
                // Swap books[i] and books[j]
                Book temp = books.get(i);
                books.set(i, books.get(j));
                books.set(j, temp);
            }
        }

        // Swap books[i+1] and books[high] (pivot)
        Book temp = books.get(i + 1);
        books.set(i + 1, books.get(high));
        books.set(high, temp);

        return i + 1; // Return partition index
    }

    public static void quickSortByIsbn(List<Book> books, int low, int high) {
        if (low < high) {
            int pi = partition(books, low, high);  // Get partition index
            quickSortByIsbn(books, low, pi - 1);   // Sort left side
            quickSortByIsbn(books, pi + 1, high);  // Sort right side
        }
    }


    /*
     * ============================= QuickSort Sort method book ends =============================
     */

}
