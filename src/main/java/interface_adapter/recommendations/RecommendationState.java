package interface_adapter.recommendations;

import java.util.List;

import entity.Book;

/**
 * Holds data for recommendation view.
 */
public class RecommendationState {

    private List<Book> recommendedBooks;

    public List<Book> getRecommendedBooks() {
        return recommendedBooks;
    }

    public void setRecommendedBooks(List<Book> recommendedBooks) {
        this.recommendedBooks = recommendedBooks;
    }
}
