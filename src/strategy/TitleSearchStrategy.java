package strategy;

import model.Book;

public class TitleSearchStrategy implements LibrarySearchStrategy {
    public java.util.List<Book> search(java.util.Collection<Book> books, String query) {
        return books.stream()
            .filter(b -> b.getAuthor().toLowerCase().contains(query.toLowerCase()))
            .collect(java.util.stream.Collectors.toList());
    }
}
