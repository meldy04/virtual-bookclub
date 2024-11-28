package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

/**
 * Class representing a book.
 */
public class Book {

    private String title;
    private String author;
    private String genre;
    private double rating;
    private List<Review> reviews;

    public Book(String title, String author, String genre, double rating) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
        this.reviews = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Adds new review to reviews list.
     * @param review the new review
     */
    public void addReview(Review review) {
        this.reviews.add(review);
    }

    private void setTitle(String newTitle) {
        this.title = newTitle;
    }

    private void setAuthor(String newAuthor) {
        this.author = newAuthor;
    }

    private void setGenre(String newGenre) {
        this.genre = newGenre;
    }

    private void setRating(double newRating) {
        this.rating = newRating;
    }

}
