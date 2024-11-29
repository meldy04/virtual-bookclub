package data_access;

import java.util.ArrayList;
import java.util.List;

import entity.Review;

/**
 * In-memory implementation of a data access object (DAO) for storing and retrieving {@link Review} objects.
 * This class provides methods to add, retrieve, and manage reviews for users in an in-memory list.
 */
public class InMemoryReviewDataAccessObject implements ReviewRepository {
    private final List<Review> reviews = new ArrayList<>();

    /**
     * Adds a review to the in-memory store.
     * @param review the review to add
     */
    @Override
    public void addReview(Review review) {
        reviews.add(review);
    }

    /**
     * Retrieves all reviews for a specific book.
     * @param bookId the ID of the book
     * @return list of reviews associated with the book
     */
    @Override
    public List<Review> getReviewsByBook(String bookId) {
        final List<Review> bookReviews = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getBook().getTitle().equals(bookId)) {
                bookReviews.add(review);
            }
        }
        return bookReviews;
    }

    /**
     * Retrieves all reviews for a specific user.
     * @param userId the ID of the user
     * @return list of reviews associated with the user
     */
    @Override
    public List<Review> getReviewsByUser(String userId) {
        final List<Review> userReviews = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getUser().getName().equals(userId)) {
                userReviews.add(review);
            }
        }
        return userReviews;
    }

    /**
     * Retrieves all reviews.
     * @return list of reviews associated with the user
     */
    @Override
    public List<Review> getAllReviews() {
        return new ArrayList<>(reviews);
    }
}
