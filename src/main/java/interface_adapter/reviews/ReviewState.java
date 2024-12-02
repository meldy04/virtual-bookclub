package interface_adapter.reviews;

import java.util.List;

import entity.Review;

/**
 * Contains review related data.
 */
public class ReviewState {

    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
