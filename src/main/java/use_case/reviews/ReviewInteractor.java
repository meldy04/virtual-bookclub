package use_case.reviews;

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
        final Review review = new Review(reviewInputData.getUser(), reviewInputData.getBook(),
                reviewInputData.getReviewText(), reviewInputData.getRating());

        dataAccess.saveReview(review);

        // TODO .
        outputBoundary.presentReviewSubmissionResult(true);
    }
}
