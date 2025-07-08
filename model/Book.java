package model;

public abstract class Book {
    private String isbn;
    private String title;
    private int publishYear;
    private double price;
    private String authorName;

    /**
     * Constructs a new Book with the specified details.
     * 
     * @param isbn        The unique identifier for the book
     * @param title       The title of the book
     * @param publishYear The year when the book was published
     * @param price       The price of the book
     * @param authorName  The name of the book's author
     */
    public Book(String isbn, String title, int publishYear, double price, String authorName) {
        this.isbn = isbn;
        this.title = title;
        this.publishYear = publishYear;
        this.price = price;
        this.authorName = authorName;
    }

    /**
     * Gets the ISBN of the book.
     * 
     * @return The ISBN string identifier
     */
    public String getISBN() {
        return isbn;
    }

    /**
     * Gets the title of the book.
     * 
     * @return The book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the year when the book was published.
     * 
     * @return The publication year
     */
    public int getPublishYear() {
        return publishYear;
    }

    /**
     * Gets the price of the book.
     * 
     * @return The book price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the author's name.
     * 
     * @return The author name
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Sets the ISBN of the book.
     * 
     * @param isbn The new ISBN
     */
    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Sets the title of the book.
     * 
     * @param title The new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the publication year of the book.
     * 
     * @param publishYear The new publication year
     */
    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    /**
     * Sets the price of the book.
     * 
     * @param price The new price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the author's name.
     * 
     * @param authorName The new author name
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * Abstract method to be implemented by subclasses to define
     * purchase behavior specific to each book type.
     * 
     * @param quantity The quantity to purchase
     * @param email    The customer's email address
     * @param address  The customer's shipping address
     * @return The total amount paid
     */
    public abstract double purchase(int quantity, String email, String address);

    /**
     * Checks if the book is available for purchase.
     * Default implementation returns true, can be overridden by subclasses.
     * 
     * @param quantity The requested quantity
     * @return true if available, false otherwise
     */
    public abstract boolean isAvailable(int quantity);

    @Override
    public String toString() {
        return String.format("Book{isbn='%s', title='%s', publishYear=%d, price=%.2f, authorName='%s'}",
                isbn, title, publishYear, price, authorName);
    }
}