package use_case.reviews;

import java.util.List;

import entity.Review;

public class ReviewInteractor implements ReviewInputBoundary {
    private final ReviewOutputBoundary outputBoundary;
    private final ReviewDataAccessInterface dataAccess;

    public ReviewInteractor(ReviewOutputBoundary outputBoundary,
                            ReviewDataAccessInterface dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    @Override
    public void submitReview(ReviewInputData reviewInputData) {
        final boolean isSuccess;
        final List<String> messages;

        final double rating = reviewInputData.getRating();

        if (rating < 0 || rating > 5) {
            isSuccess = false;
            messages = List.of("Failed to submit review: Rating must be between 0 and 5");
        }
        else {
            final Review review = new Review(reviewInputData.getUser(), reviewInputData.getBook(),
                    reviewInputData.getReviewText(), rating);

            dataAccess.saveReview(review);
            isSuccess = true;
            messages = List.of("Review submitted successfully.");
        }

        outputBoundary.presentReviewSubmissionResult(isSuccess, messages);
    }
}
