package data_access;

import java.util.ArrayList;
import java.util.List;

import entity.Book;

/**
 * In-memory implementation for storing and retrieving user book recommendations.
 */
public class InMemoryRecommendationDataAccessObject {

    private final List<UserRecommendations> userRecommendationsList = new ArrayList<>();

    /**
     * Adds recommendations for a user.
     * @param username the username of the user
     * @param recommendedBooks the list of recommended books for the user
     */
    public void addRecommendationsForUser(String username, List<Book> recommendedBooks) {
        userRecommendationsList.add(new UserRecommendations(username, recommendedBooks));
    }

    /**
     * Retrieves recommendations for a user.
     * @param username the username of the user
     * @return list of recommended books for the user
     */
    public List<Book> getRecommendationsForUser(String username) {
        List<Book> recommendedBooks = new ArrayList<>();
        for (UserRecommendations userRecommendations : userRecommendationsList) {
            if (userRecommendations.getUsername().equals(username)) {
                recommendedBooks = userRecommendations.getRecommendedBooks();
                break;
            }
        }
        return recommendedBooks;
    }

    // Helper class to store user recommendations
    private static class UserRecommendations {
        private final String username;
        private final List<Book> recommendedBooks;

        /**
         * Constructor to create a new UserRecommendations object.
         *
         * @param username the username of the user
         * @param recommendedBooks the list of books recommended for the user
         */
        UserRecommendations(String username, List<Book> recommendedBooks) {
            this.username = username;
            this.recommendedBooks = recommendedBooks;
        }

        public String getUsername() {
            return username;
        }

        public List<Book> getRecommendedBooks() {
            return recommendedBooks;
        }
    }
}
