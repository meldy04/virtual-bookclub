package data_access;

import java.util.List;

import entity.Review;

/**
 * A representation of review storage.
 */
public interface ReviewRepository {
    /**
     * Add a review.
     * @param review adds a review to the system.
     */
    void addReview(Review review);

    /**
     * Add a review.
     * @param bookId get reviews from a particular book.
     * @return reviews for a book.
     */
    List<Review> getReviewsByBook(String bookId);

    /**
     * Add a review.
     * @param userId get reviews written by a particular user.
     * @return reviews by user.
     */
    List<Review> getReviewsByUser(String userId);

    /**
     * Add a review.
     * @return all reviews.
     */
    List<Review> getAllReviews();
}
