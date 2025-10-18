package model;
import java.time.LocalDateTime;

public class BorrowRecord {
    private String recordId;
    private String patronId;
    private String isbn;
    private LocalDateTime borrowDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private static final int BORROW_PERIOD_DAYS = 14;

    public BorrowRecord(String recordId, String patronId, String isbn) {
        this.recordId = recordId;
        this.patronId = patronId;
        this.isbn = isbn;
        this.borrowDate = LocalDateTime.now();
        this.dueDate = borrowDate.plusDays(BORROW_PERIOD_DAYS);
        this.returnDate = null;
    }

    public String getRecordId() { return recordId; }
    public String getPatronId() { return patronId; }
    public String getIsbn() { return isbn; }
    public LocalDateTime getBorrowDate() { return borrowDate; }
    public LocalDateTime getDueDate() { return dueDate; }
    public LocalDateTime getReturnDate() { return returnDate; }
    public boolean isReturned() { return returnDate != null; }
    public boolean isOverdue() { 
        return !isReturned() && LocalDateTime.now().isAfter(dueDate); 
    }

    public void markAsReturned() {
        this.returnDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "BorrowRecord{" +
                "recordId='" + recordId + '\'' +
                ", patronId='" + patronId + '\'' +
                ", isbn='" + isbn + '\'' +
                ", borrowed=" + borrowDate +
                ", due=" + dueDate +
                ", returned=" + returnDate +
                '}';
    }
}
