package data_access;

import java.util.ArrayList;
import java.util.List;

import entity.Book;
import entity.User;
import use_case.recommendations.RecommendationDataAccessInterface;

/**
* In-memory implementation for storing and retrieving user book recommendations.
*/
public class InMemoryRecommendationDataAccessObject implements RecommendationDataAccessInterface {

    private final List<Book> books;

    public InMemoryRecommendationDataAccessObject(List<Book> books) {
        this.books = books;
    }

    /**
     * Gets the recommended books for a user based on genre.
     * @param user The user.
     * @return List of recommended books.
     */
    @Override
    public List<Book> getBooksBasedOnGenres(User user) {
        final List<Book> recommendations = new ArrayList<>();
        for (Book book : books) {
            if (user.getFavoriteGenres().contains(book.getGenre())) {
                recommendations.add(book);
            }
        }
        return recommendations;
    }

    /**
     * Gets the recommended books for a user based on ratings.
     * @param user The user.
     * @return List of recommended books.
     */
    @Override
    public List<Book> getBooksBasedOnRatings(User user) {
        final List<Book> recommendations = new ArrayList<>();
        for (Book book : books) {
            if (book.getRating() > 4.0) {
                recommendations.add(book);
            }
        }
        return recommendations;
    }
}
