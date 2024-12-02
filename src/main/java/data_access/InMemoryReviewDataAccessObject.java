package data_access;

import java.util.ArrayList;
import java.util.List;

import entity.Book;
import entity.Review;
import entity.User;
import use_case.reviews.ReviewDataAccessInterface;

/**
 * In-memory implementation of a data access object (DAO) for storing and retrieving {@link Review} objects.
 * This class provides methods to add, retrieve, and manage reviews for users in an in-memory list.
 */
public class InMemoryReviewDataAccessObject implements ReviewDataAccessInterface {

    private final List<Review> reviews;

    public InMemoryReviewDataAccessObject() {
        this.reviews = new ArrayList<>();
    }

    /**
     * Adds a review to the in-memory store.
     * @param review the review to add
     */
    @Override
    public void saveReview(Review review) {
        reviews.add(review);
    }

    /**
     * Retrieves all reviews for a specific book.
     * @param book .
     * @return list of reviews associated with the book
     */
    @Override
    public List<Review> getReviews(Book book) {
        final List<Review> bookReviews = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getBook().equals(book)) {
                bookReviews.add(review);
            }
        }
        return bookReviews;
    }

    /**
     * Retrieves all reviews for a specific user.
     * @param user .
     * @return list of reviews associated with the user
     */
    @Override
    public List<Review> getUserReviews(User user) {
        final List<Review> userReviews = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getUser().equals(user)) {
                userReviews.add(review);
            }
        }
        return userReviews;
    }
}
