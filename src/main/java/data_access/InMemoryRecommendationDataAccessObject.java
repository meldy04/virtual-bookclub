package data_access;

import java.util.List;

import entity.Book;
import entity.User;

/**
 * In-memory implementation for storing and retrieving user book recommendations.
 */
public class InMemoryRecommendationDataAccessObject {

    /**
     * Removes a book from a user's recommendedBooks list.
     * @param user The user whose recommendations to update.
     * @param book The book to remove.
     */
    public void ignoreRecommendation(User user, Book book) {
        if (user.getRecommendedBooks() != null) {
            user.getRecommendedBooks().remove(book);
        }
    }

    /**
     * Gets the recommended books for a user.
     * @param user The user.
     * @return List of recommended books.
     */
    public List<Book> getRecommendations(User user) {
        return user.getRecommendedBooks();
    }
}
