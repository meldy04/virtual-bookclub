package use_case.recommendations;

import java.util.List;

import entity.Book;

/**
 * Interface for accessing book recommendations data.
 * Provides methods to retrieve books based on user preferences,
 * including genres and ratings.
 */
public interface RecommendationDataAccessInterface {
    /**
     * Interface for accessing book recommendations data.
     * @param user .
     * @return list of books based on their genre.
     */
    List<Book> getBooksBasedOnGenres(String user);

    /**
     * Interface for accessing book recommendations data.
     * @param user .
     * @return  list of books based on their ratings.
     */
    List<Book> getBooksBasedOnRatings(String user);
}
