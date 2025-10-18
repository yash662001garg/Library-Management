package strategy;

import model.Book;

public class IsbnSearchStrategy implements LibrarySearchStrategy {

    public IsbnSearchStrategy() {
    }
    public java.util.List<Book> search(java.util.Collection<Book> books, String query) {
        return books.stream()
            .filter(b -> b.getIsbn().equalsIgnoreCase(query))
            .collect(java.util.stream.Collectors.toList());
    }
}
