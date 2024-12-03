package use_case.reviews;

import java.util.List;

public interface ReviewOutputBoundary {
    void presentReviewSubmissionResult(boolean isSuccess, List<String> outputReviews);
}
