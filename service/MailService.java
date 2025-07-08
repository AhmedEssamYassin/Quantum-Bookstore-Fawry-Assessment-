package service;

import model.*;

public class MailService {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private MailService() {
        // Utility class should not be instantiated
    }

    /**
     * Sends an eBook to the specified email address.
     * This method simulates the email delivery process by logging the email
     * details.
     * 
     * @param book     The eBook to be sent
     * @param quantity The quantity of eBooks to send
     * @param email    The destination email address
     */
    public static void sendEBook(EBook book, int quantity, String email) {
        System.out.println("Quantum book store: Mail Service - Preparing to send eBook:");
        System.out.println("Quantum book store: eBook: " + book.getTitle() + " (ISBN: " + book.getISBN() + ")");
        System.out.println("Quantum book store: File Type: " + book.getFileType());
        System.out.println("Quantum book store: Quantity: " + quantity);
        System.out.println("Quantum book store: Destination Email: " + email);
        System.out.println("Quantum book store: eBook sent successfully!");
    }

    /**
     * Validates if an email address is in a proper format.
     * This is a utility method for email validation.
     * 
     * @param email The email address to validate
     * @return true if the email format is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        // Simple email validation regex
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * Sends a purchase confirmation email.
     * This method simulates sending a confirmation email after a purchase.
     * 
     * @param customerEmail The customer's email address
     * @param bookTitle     The title of the purchased book
     * @param totalAmount   The total amount paid
     */
    public static void sendPurchaseConfirmation(String customerEmail, String bookTitle, double totalAmount) {
        System.out.println("Quantum book store: Mail Service - Sending purchase confirmation:");
        System.out.println("Quantum book store: To: " + customerEmail);
        System.out.println("Quantum book store: Subject: Purchase Confirmation - " + bookTitle);
        System.out.println("Quantum book store: Total Amount: $" + String.format("%.2f", totalAmount));
        System.out.println("Quantum book store: Confirmation email sent successfully!");
    }
}