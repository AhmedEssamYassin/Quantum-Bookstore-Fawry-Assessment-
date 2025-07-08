# Quantum Bookstore – Fawry Quantum Internship Challenge

This is a Java-based Quantum Bookstore designed for the Fawry Rise Journey Internship Challenge. The project demonstrates object-oriented design principles, adheres strictly to SOLID principles, and uses JavaDoc conventions for documentation.

## Project Overview

The goal of this project is to build a simple but extensible online bookstore with:

- Multiple book types:
  - Paper books (shippable, with stock)
  - EBooks (digital, with file type, sent via email)
  - Showcase/Demo books (not for sale)
- Book inventory management
- Book purchase handling
- Integration with shipping and email services (no implementation required)
- All print output is prefixed with `Quantum book store`

## Functional Requirements

- Add a book to the inventory with:
  - ISBN (any string identifier)
  - Title
  - Author name
  - Year of publication
  - Price

- Remove and return outdated books:
  - Books older than a specified number of years

- Buy a book by providing:
  - ISBN
  - Quantity
  - Email (for EBook delivery)
  - Address (for PaperBook shipping)
  
  Buying process:
  - Reduces stock for PaperBooks
  - Throws an error if not available
  - Returns paid amount
  - Sends PaperBook to `ShippingService` with address
  - Sends EBook to `MailService` with email

## Design Principles

The system follows **SOLID principles**:

- **S**ingle Responsibility: Each class has a focused responsibility (e.g., `Book`, `QuantumBookstore`)
- **O**pen/Closed: Adding a new book type doesn't require changes in existing logic
- **L**iskov Substitution: All book subtypes extend `Book` and behave consistently
- **I**nterface Segregation: Shipping and emailing are isolated behaviors
- **D**ependency Inversion: High-level operations rely on `Book` abstractions; delivery logic is delegated to specific book types.

### UML Diagram

A complete [UML Diagram](docs/UML%20Diagram.png).


## Project Structure

```
├── Main.java
├── model/
│   ├── Book.java
│   ├── EBook.java
│   ├── PaperBook.java
│   ├── ShowcaseBook.java
│   └── QuantumBookstore.java
├── service/
│   ├── ShippingService.java
│   └── MailService.java
├── test/
│   └── QuantumBookstoreFullTest.java
```

## JavaDoc Convention

All classes and public methods are documented using the official [Oracle JavaDoc style](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html).  

Each class includes:

- A class-level description  
- JavaDoc for each method, including:  
  - `@param` for input parameters  
  - `@return` for return values  
  - `@throws` where exceptions may be thrown  

Example:

```java
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
}
    .
    .
    .
```

## Features

- Add, remove, and buy books
- Check for outdated books
- Handle PaperBook and EBook delivery
- ShowcaseBooks are excluded from purchase
- Unified output prefixed with `Quantum book store`

## Build & Run

```bash
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d bin $files
cd bin
java Main
```

## Tests

[Test1](images/Test1.png)

[Test2](images/Test2.png)

[Test3](images/Test3.png)

[Test4](images/Test4.png)

[Test5](images/Test5.png)

## Author

Fawry Rise Journey Challenge – Intern Submission  
Designed and Developed by Ahmed Essam Yassin

---