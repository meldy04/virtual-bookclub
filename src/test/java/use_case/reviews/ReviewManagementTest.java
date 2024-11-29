package use_case.reviews;

import data_access.InMemoryReviewDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import entity.Book;
import entity.Review;
import entity.User;

import java.util.List;

public class ReviewManagementTest {

    private InMemoryReviewDataAccessObject reviewRepository;
    private ReviewManagement reviewManagement;

    @BeforeEach
    public void setUp() {
        reviewRepository = new InMemoryReviewDataAccessObject();
        reviewManagement = new ReviewManagement(reviewRepository);
    }

    @Test
    public void testAddReview() {
        User user = new User("Alice", "password");
        Book book = new Book("Effective Java", "Joshua Bloch", "Programming", 0);

        Review review = new Review(user, book, "Excellent book!", 5.0);

        reviewManagement.addReview(review);

        List<Review> reviews = reviewRepository.getAllReviews();
        assertEquals(1, reviews.size());
        assertEquals("Excellent book!", reviews.get(0).getText());
    }

    @Test
    public void testGetReviewsByBook() {
        User user1 = new User("Alice", "password");
        User user2 = new User("Bob", "password");

        Book book1 = new Book("Effective Java", "Joshua Bloch", "Programming", 0);
        Book book2 = new Book("Clean Code", "Robert Martin", "Programming", 0);

        Review review1 = new Review(user1, book1, "Must read for Java developers.", 4.5);
        Review review2 = new Review(user2, book1, "Very informative!", 4.8);
        Review review3 = new Review(user1, book2, "Clean Code is incredible.", 5.0);

        reviewRepository.addReview(review1);
        reviewRepository.addReview(review2);
        reviewRepository.addReview(review3);

        List<Review> book1Reviews = reviewRepository.getReviewsByBook("Effective Java");

        assertEquals(2, book1Reviews.size());
        assertTrue(book1Reviews.stream().anyMatch(review -> "Must read for Java developers.".equals(review.getText())));
        assertTrue(book1Reviews.stream().anyMatch(review -> "Very informative!".equals(review.getText())));
    }
}