package model;

import java.time.LocalDateTime;

public class Patron {
    private String patronId;
    private String name;
    private String email;
    private LocalDateTime membershipDate;
    private java.util.List<BorrowRecord> borrowHistory;

    public Patron(String patronId, String name, String email) {
        this.patronId = patronId;
        this.name = name;
        this.email = email;
        this.membershipDate = LocalDateTime.now();
        this.borrowHistory = new java.util.ArrayList<>();
    }

    // Getters and setters
    public String getPatronId() { return patronId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public LocalDateTime getMembershipDate() { return membershipDate; }
    public java.util.List<BorrowRecord> getBorrowHistory() { 
        return new java.util.ArrayList<>(borrowHistory); 
    }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    public void addBorrowRecord(BorrowRecord record) {
        borrowHistory.add(record);
    }

    public int getActiveBorrows() {
        return (int) borrowHistory.stream()
            .filter(r -> !r.isReturned())
            .count();
    }

    @Override
    public String toString() {
        return "Patron{" +
                "id='" + patronId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", activeBorrows=" + getActiveBorrows() +
                '}';
    }
}
