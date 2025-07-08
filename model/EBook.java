package model;

import model.Book;
import service.MailService;

public class EBook extends Book {
    private String fileType;

    /**
     * Constructs a new EBook with the specified details and file type.
     * 
     * @param isbn        The unique identifier for the book
     * @param title       The title of the book
     * @param publishYear The year when the book was published
     * @param price       The price of the book
     * @param authorName  The name of the book's author
     * @param fileType    The file format of the eBook (e.g., "PDF", "EPUB", "MOBI")
     */
    public EBook(String isbn, String title, int publishYear, double price, String authorName, String fileType) {
        super(isbn, title, publishYear, price, authorName);
        this.fileType = fileType;
    }

    /**
     * Gets the file type of the eBook.
     * 
     * @return The file type string
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * Sets the file type of the eBook.
     * 
     * @param fileType The new file type
     */
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    /**
     * Processes the purchase of an eBook.
     * EBooks are always available and are sent via email.
     * 
     * @param quantity The quantity to purchase
     * @param email    The customer's email address
     * @param address  The customer's shipping address (not used for eBooks)
     * @return The total amount paid
     */
    @Override
    public double purchase(int quantity, String email, String address) {
        double totalAmount = getPrice() * quantity;

        // Send eBook via email
        MailService.sendEBook(this, quantity, email);

        System.out.println("Quantum book store: EBook '" + getTitle() + "' purchased. Quantity: " + quantity
                + ", Total: $" + totalAmount);

        return totalAmount;
    }

    /**
     * EBooks are always available for purchase.
     * 
     * @param quantity The requested quantity
     * @return Always returns true as eBooks have unlimited availability
     */
    @Override
    public boolean isAvailable(int quantity) {
        return true;
    }

    @Override
    public String toString() {
        return String.format("EBook{isbn='%s', title='%s', publishYear=%d, price=%.2f, authorName='%s', fileType='%s'}",
                getISBN(), getTitle(), getPublishYear(), getPrice(), getAuthorName(), fileType);
    }
}