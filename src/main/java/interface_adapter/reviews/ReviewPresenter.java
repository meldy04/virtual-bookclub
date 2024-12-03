package interface_adapter.reviews;

import java.util.List;

import use_case.reviews.ReviewOutputBoundary;

/**
 * Output logic for reviews and updates view model.
 */
public class ReviewPresenter implements ReviewOutputBoundary {

    private final ReviewViewModel viewModel;

    public ReviewPresenter(ReviewViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentReviewSubmissionResult(boolean isSuccess, List<String> outputReviews) {
        viewModel.setReviews(outputReviews);
    }
}
