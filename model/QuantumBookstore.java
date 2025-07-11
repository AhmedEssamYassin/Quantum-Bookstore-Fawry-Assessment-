package model;

import java.util.*;
import java.util.stream.Collectors;
import service.MailService;

public class QuantumBookstore {

    private final Map<String, Book> inventory;

    /**
     * Constructs a new QuantumBookstore with an empty inventory.
     */
    public QuantumBookstore() {
        this.inventory = new HashMap<>();
    }

    /**
     * Adds a book to the inventory. If a book with the same ISBN already
     * exists, it will be replaced.
     *
     * @param book The book to add to the inventory
     */
    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        inventory.put(book.getISBN(), book);
        System.out.println(
                "Quantum book store: Added book to inventory: " + book.getTitle() + " (ISBN: " + book.getISBN() + ")");
    }

    /**
     * Removes and returns outdated books that are older than the specified
     * number of years.
     *
     * @param yearsThreshold The number of years to consider a book outdated
     * @return A list of removed outdated books
     */
    public List<Book> removeOutdatedBooks(int yearsThreshold) {
        if (yearsThreshold < 0) {
            throw new IllegalArgumentException("Years threshold cannot be negative");
        }

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int cutoffYear = currentYear - yearsThreshold;

        List<Book> outdatedBooks = new ArrayList<>();
        Iterator<Map.Entry<String, Book>> iterator = inventory.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Book> entry = iterator.next();
            Book book = entry.getValue();

            if (book.getPublishYear() < cutoffYear) {
                outdatedBooks.add(book);
                iterator.remove();
                System.out.println("Quantum book store: Removed outdated book: " + book.getTitle() + " (Published: "
                        + book.getPublishYear() + ")");
            }
        }

        return outdatedBooks;
    }

    /**
     * Purchases a book from the inventory.
     *
     * @param isbn The ISBN of the book to purchase
     * @param quantity The quantity to purchase
     * @param email The customer's email address
     * @param address The customer's shipping address
     * @return The total amount paid for the purchase
     * @throws IllegalArgumentException if the book is not found or not
     * available
     */
    public double buyBook(String isbn, int quantity, String email, String address) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be null or empty");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        Book book = inventory.get(isbn);
        if (book == null) {
            throw new IllegalArgumentException(
                    "Quantum book store: Book with ISBN " + isbn + " not found in inventory");
        }

        if (!book.isAvailable(quantity)) {
            throw new IllegalArgumentException(
                    "Quantum book store: Book " + book.getTitle() + " is not available in the requested quantity");
        }

        double totalAmount = book.purchase(quantity, email, address);

        // Send purchase confirmation email
        MailService.sendPurchaseConfirmation(email, book.getTitle(), totalAmount);

        return totalAmount;
    }

    /**
     * Finds a book in the inventory by its ISBN.
     *
     * @param isbn The ISBN of the book to find
     * @return The book if found, null otherwise
     */
    public Book findBook(String isbn) {
        return inventory.get(isbn);
    }

    /**
     * Gets all books in the inventory.
     *
     * @return A list of all books in the inventory
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(inventory.values());
    }

    /**
     * Gets all books of a specific type.
     *
     * @param bookType The class type of books to retrieve
     * @param <T> The type parameter extending Book
     * @return A list of books of the specified type
     */
    @SuppressWarnings("unchecked")
    public <T extends Book> List<T> getBooksByType(Class<T> bookType) {
        return inventory.values().stream()
                .filter(bookType::isInstance)
                .map(book -> (T) book)
                .collect(Collectors.toList());
    }

    /**
     * Gets the total number of books in the inventory.
     *
     * @return The total count of books
     */
    public int getInventorySize() {
        return inventory.size();
    }

    /**
     * Searches for books by title (case-insensitive).
     *
     * @param title The title to search for
     * @return A list of books matching the title
     */
    public List<Book> searchByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return inventory.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Searches for books by author name (case-insensitive).
     *
     * @param authorName The author name to search for
     * @return A list of books by the specified author
     */
    public List<Book> searchByAuthor(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return inventory.values().stream()
                .filter(book -> book.getAuthorName().toLowerCase().contains(authorName.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Displays the current inventory.
     */
    public void displayInventory() {
        System.out.println("Quantum book store: Current Inventory:");
        System.out.println("Quantum book store: Total books: " + inventory.size());

        if (inventory.isEmpty()) {
            System.out.println("Quantum book store: No books in inventory.");
            return;
        }

        for (Book book : inventory.values()) {
            System.out.println("Quantum book store: " + book.toString());
        }
    }
}
