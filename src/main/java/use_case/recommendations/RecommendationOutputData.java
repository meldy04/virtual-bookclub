package use_case.recommendations;

import java.util.List;

import entity.Book;

/**
 * Holds the list of recommended books for output.
 */

public class RecommendationOutputData {
    private List<Book> recommendedBooks;

    public RecommendationOutputData(List<Book> recommendedBooks) {
        this.recommendedBooks = recommendedBooks;
    }

    /**
     * Returns the number of recommended books.
     *
     * @return the size of the recommended books list.
     */

    public int size() {
        return recommendedBooks.size();
    }

    /**
     * Retrieves a book from the recommended list at a specified index.
     *
     * @param index the index of the book to retrieve.
     * @return the book at the specified index.
     */

    public Book get(int index) {
        return recommendedBooks.get(index);
    }

    public List<Book> getRecommendedBooks() {
        return recommendedBooks;
    }
}
