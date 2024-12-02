package interface_adapter.reviews;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import entity.Book;
import entity.User;
import use_case.reviews.ReviewInputBoundary;
import use_case.reviews.ReviewInputData;

/**
 * Manages review state for UI updates.
 */
public class ReviewViewModel {
    private final ReviewInputBoundary reviewInputBoundary;
    private User currentUser;
    private Book selectedBook;
    private final PropertyChangeSupport support;
    private List<String> reviews;

    public ReviewViewModel(ReviewInputBoundary reviewInputBoundary) {
        this.support = new PropertyChangeSupport(this);
        this.reviews = new ArrayList<>();

        if (reviewInputBoundary == null) {
            throw new IllegalArgumentException("ReviewInputBoundary cannot be null");
        }
        this.reviewInputBoundary = reviewInputBoundary;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        final List<String> oldReviews = this.reviews;
        this.reviews = reviews;
        support.firePropertyChange("reviews", oldReviews, reviews);
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public void setSelectedBook(Book book) {
        this.selectedBook = book;
    }

    public void submitReview(String reviewText, double rating) {
        final ReviewInputData inputData = new ReviewInputData(currentUser, selectedBook, reviewText, rating);
        reviewInputBoundary.submitReview(inputData);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
