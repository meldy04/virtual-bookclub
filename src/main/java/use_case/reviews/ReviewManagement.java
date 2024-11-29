package use_case.reviews;

import data_access.ReviewRepository;
import entity.Review;

public class ReviewManagement {
    private final ReviewRepository reviewRepository;

    public ReviewManagement(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void addReview(Review review) {
        reviewRepository.addReview(review);
    }

}
