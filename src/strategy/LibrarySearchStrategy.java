package strategy;
import model.Book;

public interface LibrarySearchStrategy {
    java.util.List<Book> search(java.util.Collection<Book> books, String query);
}
