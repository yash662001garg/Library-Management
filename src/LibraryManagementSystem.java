import model.Book;
import model.BorrowRecord;
import model.Patron;
import strategy.LibrarySearchStrategy;
import strategy.SearchStrategyFactory;

public class LibraryManagementSystem {
    private java.util.Map<String, Book> books; // ISBN -> Book
    private java.util.Map<String, Patron> patrons; // PatronId -> Patron
    private java.util.Map<String, BorrowRecord> borrowRecords; // RecordId -> BorrowRecord
    private int recordCounter;
    private int patronCounter;

    public LibraryManagementSystem() {
        this.books = new java.util.HashMap<>();
        this.patrons = new java.util.HashMap<>();
        this.borrowRecords = new java.util.HashMap<>();
        this.recordCounter = 0;
        this.patronCounter = 0;
        Logger.info("Library Management System initialized");
    }

    // ==================== BOOK MANAGEMENT ====================

    public void addBook(String isbn, String title, String author, int year, int copies) {
        if (books.containsKey(isbn)) {
            Logger.warning("Book with ISBN " + isbn + " already exists");
            return;
        }

        Book book = new Book(isbn, title, author, year, copies);
        books.put(isbn, book);
        Logger.info("Book added: " + book);
    }

    public void removeBook(String isbn) {
        if (!books.containsKey(isbn)) {
            Logger.error("Book with ISBN " + isbn + " not found");
            return;
        }

        Book removed = books.remove(isbn);
        Logger.info("Book removed: " + removed);
    }

    public void updateBook(String isbn, String newTitle, String newAuthor) {
        Book book = books.get(isbn);
        if (book == null) {
            Logger.error("Book with ISBN " + isbn + " not found");
            return;
        }

        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        Logger.info("Book updated: " + book);
    }

    public java.util.List<Book> searchBooks(String searchType, String query) {
        LibrarySearchStrategy strategy = SearchStrategyFactory.createStrategy(searchType);
        return strategy.search(books.values(), query);
    }

    public Book getBook(String isbn) {
        return books.get(isbn);
    }

    public java.util.List<Book> getAllBooks() {
        return new java.util.ArrayList<>(books.values());
    }

    // ==================== PATRON MANAGEMENT ====================

    public void addPatron(String name, String email) {
        String patronId = "P" + (++patronCounter);
        Patron patron = new Patron(patronId, name, email);
        patrons.put(patronId, patron);
        Logger.info("Patron added: " + patron);
    }

    public void updatePatron(String patronId, String name, String email) {
        Patron patron = patrons.get(patronId);
        if (patron == null) {
            Logger.error("Patron with ID " + patronId + " not found");
            return;
        }

        patron.setName(name);
        patron.setEmail(email);
        Logger.info("Patron updated: " + patron);
    }

    public Patron getPatron(String patronId) {
        return patrons.get(patronId);
    }

    public java.util.List<Patron> getAllPatrons() {
        return new java.util.ArrayList<>(patrons.values());
    }

    // ==================== LENDING PROCESS ====================

    public boolean borrowBook(String patronId, String isbn) {
        Patron patron = patrons.get(patronId);
        Book book = books.get(isbn);

        if (patron == null) {
            Logger.error("Patron with ID " + patronId + " not found");
            return false;
        }

        if (book == null) {
            Logger.error("Book with ISBN " + isbn + " not found");
            return false;
        }

        if (!book.borrowCopy()) {
            Logger.warning("No available copies of book: " + book.getTitle());
            return false;
        }

        String recordId = "BR" + (++recordCounter);
        BorrowRecord record = new BorrowRecord(recordId, patronId, isbn);
        borrowRecords.put(recordId, record);
        patron.addBorrowRecord(record);

        Logger.info("Book borrowed: " + record);
        return true;
    }

    public boolean returnBook(String recordId) {
        BorrowRecord record = borrowRecords.get(recordId);

        if (record == null) {
            Logger.error("Borrow record with ID " + recordId + " not found");
            return false;
        }

        if (record.isReturned()) {
            Logger.warning("Book already returned for record: " + recordId);
            return false;
        }

        Book book = books.get(record.getIsbn());
        Patron patron = patrons.get(record.getPatronId());

        if (book == null || patron == null) {
            Logger.error("Invalid book or patron in borrow record");
            return false;
        }

        book.returnCopy();
        record.markAsReturned();

        String status = record.isOverdue() ? "OVERDUE" : "ON TIME";
        Logger.info("Book returned: " + record + " [" + status + "]");
        return true;
    }

    public java.util.List<BorrowRecord> getPatronBorrowHistory(String patronId) {
        Patron patron = patrons.get(patronId);
        if (patron == null) {
            Logger.error("Patron with ID " + patronId + " not found");
            return new java.util.ArrayList<>();
        }
        return patron.getBorrowHistory();
    }

    public java.util.List<BorrowRecord> getOverdueBooks() {
        return borrowRecords.values().stream()
            .filter(BorrowRecord::isOverdue)
            .collect(java.util.stream.Collectors.toList());
    }

    public void displayLibraryStatus() {
        Logger.info("========== LIBRARY STATUS ==========");
        Logger.info("Total Books: " + books.size());
        Logger.info("Total Patrons: " + patrons.size());
        Logger.info("Active Borrows: " + 
            borrowRecords.values().stream().filter(r -> !r.isReturned()).count());
        Logger.info("Overdue Books: " + getOverdueBooks().size());
        Logger.info("====================================");
    }
}
