package strategy;

public class SearchStrategyFactory {
    public static LibrarySearchStrategy createStrategy(String searchType) {
        return switch (searchType.toLowerCase()) {
            case "title" -> new TitleSearchStrategy();
            case "author" -> new AuthorSearchStrategy();
            case "isbn" -> new IsbnSearchStrategy();
            default -> throw new IllegalArgumentException("Unknown search type: " + searchType);
        };
    }
}
