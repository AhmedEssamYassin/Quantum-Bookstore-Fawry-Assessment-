package service;

import model.*;

public class ShippingService {

    /**
     * Private constructor to prevent instantiation of this utility class.
     */
    private ShippingService() {
        // Utility class should not be instantiated
    }

    /**
     * Ships a paper book to the specified address.
     * This method simulates the shipping process by logging the shipping details.
     * 
     * @param book     The paper book to be shipped
     * @param quantity The quantity of books to ship
     * @param address  The destination address for shipping
     */
    public static void shipBook(PaperBook book, int quantity, String address) {
        System.out.println("Quantum book store: Shipping Service - Preparing to ship:");
        System.out.println("Quantum book store: Book: " + book.getTitle() + " (ISBN: " + book.getISBN() + ")");
        System.out.println("Quantum book store: Quantity: " + quantity);
        System.out.println("Quantum book store: Destination: " + address);
        System.out.println("Quantum book store: Shipping initiated successfully!");
    }

    /**
     * Calculates estimated shipping cost for a paper book.
     * This is a utility method that can be used for cost estimation.
     * 
     * @param book     The paper book to calculate shipping for
     * @param quantity The quantity of books
     * @param address  The destination address
     * @return The estimated shipping cost
     */
    public static double calculateShippingCost(PaperBook book, int quantity, String address) {
        // Simple shipping cost calculation
        double baseCost = 5.0;
        double weightCost = quantity * 2.0; // $2 per book for weight
        return baseCost + weightCost;
    }
}