package interface_adapter.reviews;

import entity.Book;
import entity.User;
import use_case.reviews.ReviewInputBoundary;
import use_case.reviews.ReviewInputData;

/**
 * Middleman UI and Review Use Case. TODO.
 */
public class ReviewController {

    private final ReviewInputBoundary useCase;

    public ReviewController(ReviewInputBoundary useCase) {
        this.useCase = useCase;
    }

    public void submitReview(User user, Book book, String reviewText, double rating) {
        final ReviewInputData inputData = new ReviewInputData(user, book, reviewText, rating);
        useCase.submitReview(inputData);
    }
}
