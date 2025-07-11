package model;

public class ShowcaseBook extends Book {

    /**
     * Constructs a new ShowcaseBook with the specified details.
     *
     * @param isbn The unique identifier for the book
     * @param title The title of the book
     * @param publishYear The year when the book was published
     * @param price The display price of the book (for reference only)
     * @param authorName The name of the book's author
     */
    public ShowcaseBook(String isbn, String title, int publishYear, double price, String authorName) {
        super(isbn, title, publishYear, price, authorName);
    }

    /**
     * Showcase books are never available for purchase.
     *
     * @param quantity The requested quantity
     * @return Always returns false as showcase books cannot be purchased
     */
    @Override
    public boolean isAvailable(int quantity) {
        return false;
    }

    /**
     * Attempts to purchase a showcase book will always throw an exception.
     *
     * @param quantity The quantity to purchase
     * @param email The customer's email address
     * @param address The customer's shipping address
     * @return Never returns as it always throws an exception
     * @throws UnsupportedOperationException Always thrown as showcase books
     * cannot be purchased
     */
    @Override
    public double purchase(int quantity, String email, String address) {
        throw new UnsupportedOperationException(
                "Quantum book store: Showcase books are not available for purchase: " + getTitle());
    }

    @Override
    public String toString() {
        return String.format("ShowcaseBook{isbn='%s', title='%s', publishYear=%d, price=%.2f, authorName='%s'}",
                getISBN(), getTitle(), getPublishYear(), getPrice(), getAuthorName());
    }
}
