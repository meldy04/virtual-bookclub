package use_case.recommendations;

import java.util.List;

import entity.Book;

public class RecommendationOutputData {
    private List<Book> recommendedBooks;

    public RecommendationOutputData(List<Book> recommendedBooks) {
        this.recommendedBooks = recommendedBooks;
    }

    public int size() {
        return recommendedBooks.size();
    }

    public Book get(int index) {
        return recommendedBooks.get(index);
    }

    public List<Book> getRecommendedBooks() {
        return recommendedBooks;
    }
}
