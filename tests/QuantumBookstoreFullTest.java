package tests;

import java.util.List;
import model.*;

public class QuantumBookstoreFullTest {

    private final QuantumBookstore bookstore;
    private int testsPassed = 0;
    private int totalTests = 0;

    /**
     * Constructs a new test instance and initializes the bookstore.
     */
    public QuantumBookstoreFullTest() {
        this.bookstore = new QuantumBookstore();
    }

    /**
     * Runs all the tests for the QuantumBookstore system.
     */
    public void runAllTests() {
        System.out.println("Quantum Bookstore: Starting comprehensive tests...\n");

        testAddingBooks();
        testRemovingOutdatedBooks();
        testBuyingBooks();
        testErrorScenarios();
        testSearchFunctionality();
        testInventoryManagement();

        System.out.println("\nQuantum Bookstore: Test Summary:");
        System.out.printf("Quantum Bookstore: Tests passed: %d/%d%n", testsPassed, totalTests);
        System.out.printf("Quantum Bookstore: Success rate: %.2f%%%n",
                totalTests > 0 ? (double) testsPassed / totalTests * 100 : 0.0);
    }

    /**
     * Tests adding different types of books to the inventory.
     */
    private void testAddingBooks() {
        System.out.println("Quantum Bookstore: Testing book addition...");

        // Test adding PaperBook
        PaperBook paperBook = new PaperBook("978-0134685991", "Effective Java", 2018, 45.99, "Joshua Bloch", 10);
        bookstore.addBook(paperBook);
        assertTrue("Paper book should be added successfully", bookstore.findBook("978-0134685991") != null);

        // Test adding EBook
        EBook eBook = new EBook("978-0135166307", "Clean Code", 2008, 29.99, "Robert C. Martin", "PDF");
        bookstore.addBook(eBook);
        assertTrue("EBook should be added successfully", bookstore.findBook("978-0135166307") != null);

        // Test adding ShowcaseBook
        ShowcaseBook showcaseBook = new ShowcaseBook("978-0132350884", "Clean Architecture", 2017, 39.99,
                "Robert C. Martin");
        bookstore.addBook(showcaseBook);
        assertTrue("Showcase book should be added successfully", bookstore.findBook("978-0132350884") != null);

        // Test adding null book
        try {
            bookstore.addBook(null);
            assertFalse("Should not allow null book", true);
        } catch (IllegalArgumentException e) {
            assertTrue("Should throw exception for null book", true);
        }

        System.out.println("Quantum Bookstore: Book addition tests completed.\n");
    }

    /**
     * Tests removing outdated books from the inventory.
     */
    private void testRemovingOutdatedBooks() {
        System.out.println("Quantum Bookstore: Testing removal of outdated books...");

        // Add some old books
        PaperBook oldBook1 = new PaperBook("978-0000000001", "Old Book 1", 2000, 19.99, "Old Author 1", 5);
        PaperBook oldBook2 = new PaperBook("978-0000000002", "Old Book 2", 1995, 15.99, "Old Author 2", 3);
        EBook recentBook = new EBook("978-0000000003", "Recent Book", 2023, 24.99, "Recent Author", "EPUB");

        bookstore.addBook(oldBook1);
        bookstore.addBook(oldBook2);
        bookstore.addBook(recentBook);

        // Remove books older than 20 years
        List<Book> removedBooks = bookstore.removeOutdatedBooks(20);
        assertTrue("Should remove old books", removedBooks.size() == 2);
        assertTrue("Recent book should remain", bookstore.findBook("978-0000000003") != null);
        assertTrue("Old book should be removed", bookstore.findBook("978-0000000001") == null);

        // Test negative years threshold
        try {
            bookstore.removeOutdatedBooks(-1);
            assertFalse("Should not allow negative years", true);
        } catch (IllegalArgumentException e) {
            assertTrue("Should throw exception for negative years", true);
        }

        System.out.println("Quantum Bookstore: Outdated books removal tests completed.\n");
    }

    /**
     * Tests buying different types of books.
     */
    private void testBuyingBooks() {
        System.out.println("Quantum Bookstore: Testing book purchases...");

        // Add fresh books for testing
        PaperBook paperBook = new PaperBook("978-1111111111", "Java Programming", 2023, 59.99, "Test Author", 5);
        EBook eBook = new EBook("978-2222222222", "Python Guide", 2023, 39.99, "Python Expert", "PDF");
        ShowcaseBook showcaseBook = new ShowcaseBook("978-3333333333", "Display Book", 2023, 49.99, "Display Author");

        bookstore.addBook(paperBook);
        bookstore.addBook(eBook);
        bookstore.addBook(showcaseBook);

        // Test buying paper book
        try {
            double amount = bookstore.buyBook("978-1111111111", 2, "customer@email.com", "123 Main St");
            assertTrue("Paper book purchase should succeed", Math.abs(amount - 119.98) < 0.01);
            PaperBook updatedPaper = (PaperBook) bookstore.findBook("978-1111111111");
            assertTrue("Stock should be reduced", updatedPaper.getStock() == 3);
        } catch (Exception e) {
            assertFalse("Paper book purchase should not fail", true);
        }

        // Test buying eBook
        try {
            double amount = bookstore.buyBook("978-2222222222", 1, "customer@email.com", "123 Main St");
            assertTrue("EBook purchase should succeed", Math.abs(amount - 39.99) < 0.01);
        } catch (Exception e) {
            assertFalse("EBook purchase should not fail", true);
        }

        // Test buying showcase book (should fail)
        try {
            bookstore.buyBook("978-3333333333", 1, "customer@email.com", "123 Main St");
            assertFalse("Showcase book purchase should fail", true);
        } catch (IllegalArgumentException e) {
            System.out.println("Quantum Bookstore: Exception caught in showcase book purchase: " + e.getMessage());
            assertTrue("Showcase book purchase should throw exception", true);
        }

        System.out.println("Quantum Bookstore: Book purchase tests completed.\n");
    }

    /**
     * Tests various error scenarios.
     */
    private void testErrorScenarios() {
        System.out.println("Quantum Bookstore: Testing error scenarios...");

        PaperBook limitedStock = new PaperBook("978-4444444444", "Limited Book", 2023, 29.99, "Limited Author", 2);
        bookstore.addBook(limitedStock);

        // Test insufficient stock
        try {
            bookstore.buyBook("978-4444444444", 5, "customer@email.com", "123 Main St");
            assertFalse("Should fail with insufficient stock", true);
        } catch (IllegalArgumentException e) {
            assertTrue("Should throw exception for insufficient stock", true);
        }

        // Test non-existent book
        try {
            bookstore.buyBook("978-9999999999", 1, "customer@email.com", "123 Main St");
            assertFalse("Should fail with non-existent book", true);
        } catch (IllegalArgumentException e) {
            assertTrue("Should throw exception for non-existent book", true);
        }

        // Test invalid quantity
        try {
            bookstore.buyBook("978-4444444444", 0, "customer@email.com", "123 Main St");
            assertFalse("Should fail with zero quantity", true);
        } catch (IllegalArgumentException e) {
            assertTrue("Should throw exception for zero quantity", true);
        }

        // Test empty email
        try {
            bookstore.buyBook("978-4444444444", 1, "", "123 Main St");
            assertFalse("Should fail with empty email", true);
        } catch (IllegalArgumentException e) {
            assertTrue("Should throw exception for empty email", true);
        }

        // Test empty ISBN
        try {
            bookstore.buyBook("", 1, "customer@email.com", "123 Main St");
            assertFalse("Should fail with empty ISBN", true);
        } catch (IllegalArgumentException e) {
            assertTrue("Should throw exception for empty ISBN", true);
        }

        System.out.println("Quantum Bookstore: Error scenario tests completed.\n");
    }

    /**
     * Tests search functionality.
     */
    private void testSearchFunctionality() {
        System.out.println("Quantum Bookstore: Testing search functionality...");

        // Add books for search testing
        PaperBook searchBook1 = new PaperBook("978-5555555555", "Advanced Java", 2023, 65.99, "Java Master", 10);
        EBook searchBook2 = new EBook("978-6666666666", "Java Basics", 2023, 35.99, "Java Beginner", "PDF");
        bookstore.addBook(searchBook1);
        bookstore.addBook(searchBook2);

        // Test search by title
        List<Book> titleResults = bookstore.searchByTitle("Java");
        assertTrue("Should find books with 'Java' in title", titleResults.size() >= 2);

        // Test search by author
        List<Book> authorResults = bookstore.searchByAuthor("Java Master");
        assertTrue("Should find books by 'Java Master'", authorResults.size() >= 1);

        // Test search with empty string
        List<Book> emptyResults = bookstore.searchByTitle("");
        assertTrue("Empty search should return empty list", emptyResults.isEmpty());

        // Test search with null
        List<Book> nullResults = bookstore.searchByAuthor(null);
        assertTrue("Null search should return empty list", nullResults.isEmpty());

        System.out.println("Quantum Bookstore: Search functionality tests completed.\n");
    }

    /**
     * Tests inventory management operations.
     */
    private void testInventoryManagement() {
        System.out.println("Quantum Bookstore: Testing inventory management...");

        // Test getting books by type
        List<PaperBook> paperBooks = bookstore.getBooksByType(PaperBook.class);
        List<EBook> eBooks = bookstore.getBooksByType(EBook.class);
        List<ShowcaseBook> showcaseBooks = bookstore.getBooksByType(ShowcaseBook.class);

        assertTrue("Should have paper books", !paperBooks.isEmpty());
        assertTrue("Should have eBooks", !eBooks.isEmpty());
        assertTrue("Should have showcase books", !showcaseBooks.isEmpty());

        // Test inventory size
        int totalBooks = bookstore.getInventorySize();
        assertTrue("Inventory should have books", totalBooks > 0);

        // Test get all books
        List<Book> allBooks = bookstore.getAllBooks();
        assertTrue("All books count should match inventory size", allBooks.size() == totalBooks);

        // Test display inventory (visual test)
        bookstore.displayInventory();

        System.out.println("Quantum Bookstore: Inventory management tests completed.\n");
    }

    /**
     * Utility method to assert test conditions.
     *
     * @param message The test message
     * @param condition The condition to test
     */
    private void assertTrue(String message, boolean condition) {
        totalTests++;
        if (condition) {
            testsPassed++;
            System.out.println("Quantum Bookstore: TEST PASSED: " + message);
        } else {
            System.out.println("Quantum Bookstore: TEST FAILED: " + message);
        }
    }

    /**
     * Utility method to assert false conditions.
     *
     * @param message The test message
     * @param condition The condition to test (should be false)
     */
    private void assertFalse(String message, boolean condition) {
        assertTrue(message, !condition);
    }

    /**
     * Main method to run the tests.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        QuantumBookstoreFullTest test = new QuantumBookstoreFullTest();
        test.runAllTests();
    }
}
