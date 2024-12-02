package use_case.recommendations;

import java.util.List;

import entity.Book;
import entity.User;

public interface RecommendationDataAccessInterface {
    List<Book> getBooksBasedOnGenres(User user);
    List<Book> getBooksBasedOnRatings(User user);
}
