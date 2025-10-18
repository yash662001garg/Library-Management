public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        LibraryManagementSystem library = new LibraryManagementSystem();

        // Add books
        library.addBook("978-0134685991", "Effective Java", "Joshua Bloch", 2018, 3);
        library.addBook("978-0596007126", "Head First Design Patterns", "Freeman & Freeman", 2004, 2);
        library.addBook("978-0201633610", "Design Patterns", "Gang of Four", 1994, 1);

        // Add patrons
        library.addPatron("John Doe", "john@example.com");
        library.addPatron("Jane Smith", "jane@example.com");

        // Search books
        Logger.info("Search by title 'Java':");
        library.searchBooks("title", "Java").forEach(b -> Logger.info(b.toString()));

        // Borrow books
        library.borrowBook("P1", "978-0134685991");
        library.borrowBook("P1", "978-0596007126");
        library.borrowBook("P2", "978-0134685991");

        // Display status
        library.displayLibraryStatus();

        // Return book
        library.returnBook("BR1");

        // Get patron history
        Logger.info("Patron P1 borrow history:");
        library.getPatronBorrowHistory("P1").forEach(r -> Logger.info(r.toString()));
    }
}
