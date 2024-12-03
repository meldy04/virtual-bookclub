package use_case.reviews;

import entity.Book;
import entity.User;

public class ReviewInputData {
    private final User user;
    private final Book book;
    private final String reviewText;
    private final double rating;

    public ReviewInputData(User user, Book book, String reviewText, double rating) {
        this.user = user;
        this.book = book;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public String getReviewText() {
        return reviewText;
    }

    public double getRating() {
        return rating;
    }
}
