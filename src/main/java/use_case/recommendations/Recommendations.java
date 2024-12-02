package use_case.recommendations;

import java.util.ArrayList;
import java.util.List;

import entity.Book;
import entity.User;

/**
 * Implementation of the Recommendations class.
 */

public class Recommendations {

    private final BookRecommendationApi bookRecommendationApi;

    public Recommendations(BookRecommendationApi bookRecommendationApi) {
        this.bookRecommendationApi = bookRecommendationApi;
    }

    /**
     * RecommendedBooks function.
     * @param user User
     * @return A list of recommended books for the user
     */
    public List<Book> recommendBooks(User user) {

        final List<Book> recommended = new ArrayList<>();

        for (Book book : user.getReadBooks()) {
            final String genre = book.getGenre();
            final List<Book> genreRecommendations = bookRecommendationApi.fetchBooksByGenre(genre);

            for (Book recommendedBook : genreRecommendations) {
                if (!user.getReadBooks().contains(recommendedBook)
                        && !user.getBooksToRead().contains(recommendedBook)
                        && !recommended.contains(recommendedBook)) {
                    recommended.add(recommendedBook);
                }
            }
        }

        user.getRecommendedBooks().addAll(recommended);
        return recommended;
    }
}
