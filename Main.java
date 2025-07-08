import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

// I strongly recommend reading the README file carefully first 
// to know all considerations and conventions in this project
import model.*;
import service.*;

public class Main {

    /**
     * Main method to run the demonstration.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("\nQuantum book store: Welcome to the Quantum Bookstore Demo!");
        System.out.println("Quantum book store: ===============================================\n");

        // Create a new bookstore instance
        QuantumBookstore bookstore = new QuantumBookstore();

        // Demonstrate adding different types of books
        demonstrateAddingBooks(bookstore);

        // Demonstrate inventory management
        demonstrateInventoryManagement(bookstore);

        // Demonstrate purchasing books
        demonstratePurchasing(bookstore);

        // Demonstrate removing outdated books
        demonstrateRemovingOutdatedBooks(bookstore);

        // Demonstrate search functionality
        demonstrateSearchFunctionality(bookstore);

        System.out.println("\nQuantum book store: Demo completed successfully!");
        System.out.println("Quantum book store: Thank you for using Quantum Bookstore!");
    }

    /**
     * Demonstrates adding different types of books to the inventory.
     * 
     * @param bookstore The bookstore instance to use
     */
    private static void demonstrateAddingBooks(QuantumBookstore bookstore) {
        System.out.println("Quantum book store: === ADDING BOOKS TO INVENTORY ===");

        // Add paper books
        PaperBook paperBook1 = new PaperBook("978-0134685991", "Effective Java", 2018, 45.99, "Joshua Bloch", 15);
        PaperBook paperBook2 = new PaperBook("978-0134494166", "Clean Code", 2008, 39.99, "Robert C. Martin", 20);
        PaperBook paperBook3 = new PaperBook("978-0596009205", "Head First Design Patterns", 2004, 49.99,
                "Eric Freeman", 8);

        bookstore.addBook(paperBook1);
        bookstore.addBook(paperBook2);
        bookstore.addBook(paperBook3);

        // Add eBooks
        EBook eBook1 = new EBook("978-0135166307", "Java: The Complete Reference", 2020, 29.99, "Herbert Schildt",
                "PDF");
        EBook eBook2 = new EBook("978-0134757599", "Refactoring", 2018, 34.99, "Martin Fowler", "EPUB");
        EBook eBook3 = new EBook("978-0321356680", "Effective C++", 2005, 24.99, "Scott Meyers", "PDF");

        bookstore.addBook(eBook1);
        bookstore.addBook(eBook2);
        bookstore.addBook(eBook3);

        // Add showcase books
        ShowcaseBook showcaseBook1 = new ShowcaseBook("978-0132350884", "Clean Architecture", 2017, 42.99,
                "Robert C. Martin");
        ShowcaseBook showcaseBook2 = new ShowcaseBook("978-0321125215", "Domain-Driven Design", 2003, 54.99,
                "Eric Evans");

        bookstore.addBook(showcaseBook1);
        bookstore.addBook(showcaseBook2);

        System.out.println("Quantum book store: All books added successfully!\n");
    }

    /**
     * Demonstrates inventory management features.
     * 
     * @param bookstore The bookstore instance to use
     */
    private static void demonstrateInventoryManagement(QuantumBookstore bookstore) {
        System.out.println("Quantum book store: === INVENTORY MANAGEMENT ===");

        // Display current inventory
        bookstore.displayInventory();

        // Show books by type
        System.out.println("\nQuantum book store: Paper Books:");
        bookstore.getBooksByType(PaperBook.class).forEach(book -> System.out
                .println("Quantum book store: - " + book.getTitle() + " (Stock: " + book.getStock() + ")"));

        System.out.println("\nQuantum book store: EBooks:");
        bookstore.getBooksByType(EBook.class).forEach(book -> System.out
                .println("Quantum book store: - " + book.getTitle() + " (Format: " + book.getFileType() + ")"));

        System.out.println("\nQuantum book store: Showcase Books:");
        bookstore.getBooksByType(ShowcaseBook.class)
                .forEach(book -> System.out.println("Quantum book store: - " + book.getTitle() + " (Display Only)"));

        System.out.println();
    }

    /**
     * Demonstrates purchasing different types of books.
     * 
     * @param bookstore The bookstore instance to use
     */
    private static void demonstratePurchasing(QuantumBookstore bookstore) {
        System.out.println("Quantum book store: === PURCHASING BOOKS ===");

        try {
            // Purchase paper book
            System.out.println("Quantum book store: Purchasing paper book...");
            double amount1 = bookstore.buyBook("978-0134685991", 2, "customer1@email.com", "123 Main St, City, State");
            System.out.println("Quantum book store: Purchase successful! Amount paid: $" + amount1 + "\n");

            // Purchase eBook
            System.out.println("Quantum book store: Purchasing eBook...");
            double amount2 = bookstore.buyBook("978-0135166307", 1, "customer2@email.com", "456 Oak Ave, City, State");
            System.out.println("Quantum book store: Purchase successful! Amount paid: $" + amount2 + "\n");

            // Try to purchase showcase book (should fail)
            System.out.println("Quantum book store: Attempting to purchase showcase book...");
            try {
                bookstore.buyBook("978-0132350884", 1, "customer3@email.com", "789 Pine Rd, City, State");
            } catch (UnsupportedOperationException e) {
                System.out.println("Quantum book store: Purchase failed as expected: " + e.getMessage() + "\n");
            }

            // Try to purchase more than available stock
            System.out.println("Quantum book store: Attempting to purchase more than available stock...");
            try {
                bookstore.buyBook("978-0596009205", 20, "customer4@email.com", "321 Elm St, City, State");
            } catch (IllegalArgumentException e) {
                System.out.println("Quantum book store: Purchase failed as expected: " + e.getMessage() + "\n");
            }

        } catch (Exception e) {
            System.out.println("Quantum book store: Unexpected error: " + e.getMessage() + "\n");
        }
    }

    /**
     * Demonstrates removing outdated books from inventory.
     * 
     * @param bookstore The bookstore instance to use
     */
    private static void demonstrateRemovingOutdatedBooks(QuantumBookstore bookstore) {
        System.out.println("Quantum book store: === REMOVING OUTDATED BOOKS ===");

        // Add some old books for demonstration
        PaperBook oldBook1 = new PaperBook("978-0000000001", "Ancient Programming", 1990, 19.99, "Old Programmer", 3);
        PaperBook oldBook2 = new PaperBook("978-0000000002", "Legacy Systems", 1995, 25.99, "Legacy Expert", 5);

        bookstore.addBook(oldBook1);
        bookstore.addBook(oldBook2);

        System.out.println("Quantum book store: Added old books for demonstration");
        System.out.println("Quantum book store: Current inventory size: " + bookstore.getInventorySize());

        // Remove books older than 15 years
        System.out.println("Quantum book store: Removing books older than 15 years...");
        bookstore.removeOutdatedBooks(15);

        System.out.println("Quantum book store: New inventory size: " + bookstore.getInventorySize() + "\n");
    }

    /**
     * Demonstrates search functionality.
     * 
     * @param bookstore The bookstore instance to use
     */
    private static void demonstrateSearchFunctionality(QuantumBookstore bookstore) {
        System.out.println("Quantum book store: === SEARCH FUNCTIONALITY ===");

        // Search by title
        System.out.println("Quantum book store: Searching for books with 'Java' in title:");
        bookstore.searchByTitle("Java").forEach(
                book -> System.out.println("Quantum book store: - " + book.getTitle() + " by " + book.getAuthorName()));

        // Search by author
        System.out.println("\nQuantum book store: Searching for books by 'Robert C. Martin':");
        bookstore.searchByAuthor("Robert C. Martin").forEach(book -> System.out
                .println("Quantum book store: - " + book.getTitle() + " (" + book.getPublishYear() + ")"));

        // Search for non-existent book
        System.out.println("\nQuantum book store: Searching for non-existent book:");
        var results = bookstore.searchByTitle("Non-existent Book");
        if (results.isEmpty()) {
            System.out.println("Quantum book store: No books found matching the search criteria");
        }

        System.out.println();
    }

}