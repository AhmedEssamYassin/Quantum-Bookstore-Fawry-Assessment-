package model;

import model.Book;
import service.*;

public class PaperBook extends Book {
    private int stock;

    /**
     * Constructs a new PaperBook with the specified details and stock quantity.
     * 
     * @param isbn        The unique identifier for the book
     * @param title       The title of the book
     * @param publishYear The year when the book was published
     * @param price       The price of the book
     * @param authorName  The name of the book's author
     * @param stock       The initial stock quantity
     */
    public PaperBook(String isbn, String title, int publishYear, double price, String authorName, int stock) {
        super(isbn, title, publishYear, price, authorName);
        this.stock = stock;
    }

    /**
     * Gets the current stock quantity of the paper book.
     * 
     * @return The current stock count
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the stock quantity of the paper book.
     * 
     * @param stock The new stock quantity
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Reduces the stock by the specified quantity.
     * 
     * @param quantity The quantity to reduce
     * @throws IllegalArgumentException if quantity is greater than available stock
     */
    public void reduceStock(int quantity) {
        if (quantity > stock) {
            throw new IllegalArgumentException("Insufficient stock. Available: " + stock + ", Requested: " + quantity);
        }
        stock -= quantity;
    }

    /**
     * Checks if the requested quantity is available in stock.
     * 
     * @param quantity The requested quantity
     * @return true if sufficient stock is available, false otherwise
     */
    @Override
    public boolean isAvailable(int quantity) {
        return stock >= quantity;
    }

    /**
     * Processes the purchase of paper books.
     * Reduces stock and sends the book to shipping service.
     * 
     * @param quantity The quantity to purchase
     * @param email    The customer's email address
     * @param address  The customer's shipping address
     * @return The total amount paid
     * @throws IllegalArgumentException if insufficient stock is available
     */
    @Override
    public double purchase(int quantity, String email, String address) {
        if (!isAvailable(quantity)) {
            throw new IllegalArgumentException("Insufficient stock for paper book: " + getTitle());
        }

        reduceStock(quantity);
        double totalAmount = getPrice() * quantity;

        // Send to shipping service
        ShippingService.shipBook(this, quantity, address);

        System.out.println("Quantum book store: Paper book '" + getTitle() + "' purchased. Quantity: " + quantity
                + ", Total: $" + totalAmount);

        return totalAmount;
    }

    @Override
    public String toString() {
        return String.format("PaperBook{isbn='%s', title='%s', publishYear=%d, price=%.2f, authorName='%s', stock=%d}",
                getISBN(), getTitle(), getPublishYear(), getPrice(), getAuthorName(), stock);
    }
}