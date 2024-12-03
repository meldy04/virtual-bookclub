package use_case.reviews;

import java.util.List;

import entity.Book;
import entity.Review;
import entity.User;

public interface ReviewDataAccessInterface {
    void saveReview(Review review);

    List<Review> getReviews(Book book);

    List<Review> getUserReviews(User user);
}
