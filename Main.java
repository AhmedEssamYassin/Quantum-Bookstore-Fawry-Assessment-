// I strongly recommend reading the README file carefully first 
// to know all considerations and conventions in this project

import tests.QuantumBookstoreFullTest;

public class Main {

    private static final String PREFIX = "Quantum book store: ";

    /**
     * Main method to run the Quantum Bookstore test suite.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        printHeader("Welcome to the Quantum Bookstore Test Suite!");

        // Run automated tests
        printHeader("Running Automated Tests");
        QuantumBookstoreFullTest test = new QuantumBookstoreFullTest();
        test.runAllTests();

        printHeader("Test Suite Execution Completed!");
    }

    /**
     * Prints a formatted section header.
     *
     * @param message The header message
     */
    private static void printHeader(String message) {
        System.out.println("\n" + PREFIX + message);
        System.out.println(PREFIX + "=".repeat(message.length()));
    }
}
