package use_case.reviews;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import entity.Book;
import entity.Review;
import entity.User;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class ReviewInteractorTest {

    private ReviewOutputBoundary mockOutputBoundary;
    private ReviewDataAccessInterface mockDataAccess;
    private ReviewInteractor reviewInteractor;

    @Before
    public void setUp() {
        mockOutputBoundary = mock(ReviewOutputBoundary.class);
        mockDataAccess = mock(ReviewDataAccessInterface.class);
        reviewInteractor = new ReviewInteractor(mockOutputBoundary, mockDataAccess);
    }

    @Test
    public void testSubmitReview_success() {
        User testUser = new User("testUser", "password123");
        Book testBook = new Book("Test Book", "Author", "Fiction", 4.0);
        ReviewInputData inputData = new ReviewInputData(testUser, testBook, "Great Book!", 4.5);

        reviewInteractor.submitReview(inputData);

        ArgumentCaptor<Review> reviewCaptor = ArgumentCaptor.forClass(Review.class);
        verify(mockDataAccess).saveReview(reviewCaptor.capture());
        Review capturedReview = reviewCaptor.getValue();

        assert capturedReview.getUser().equals(testUser);
        assert capturedReview.getBook().equals(testBook);
        assert capturedReview.getText().equals("Great Book!");
        assert capturedReview.getRating() == 4.5;

        verify(mockOutputBoundary).presentReviewSubmissionResult(true, Collections.
                singletonList("Review submitted successfully."));
    }

    @Test
    public void testSubmitReview_invalidRating() {
        User testUser = new User("testUser", "password123");
        Book testBook = new Book("Test Book", "Author", "Fiction", 4.0);
        ReviewInputData inputData = new ReviewInputData(testUser, testBook, "Bad Book :(", 6.0);

        try {
            reviewInteractor.submitReview(inputData);
        } catch (IllegalArgumentException e) {
            verifyNoInteractions(mockDataAccess);
            verify(mockOutputBoundary).presentReviewSubmissionResult(false, Collections.singletonList
                    ("Failed to submit review: Rating must be between 0 and 5"));
        }
    }
}