package use_case.reviews;

import java.util.List;
import entity.Review;

public class ReviewOutputData {
    private final List<Review> reviews;
    private final boolean isSuccess;
    private final String message;

    public ReviewOutputData(List<Review> reviews, boolean isSuccess, String message) {
        this.reviews = reviews;
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }
}
