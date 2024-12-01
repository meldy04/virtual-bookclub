package entity;

/**
 * A representation of the reviews.
 */

public class Review {

    private String username;
    private Book book;
    private String text;
    private double rating;

    public Review(String username, Book book, String text, double rating) {
        this.username = username;
        this.book = book;
        this.text = text;
        this.rating = rating;
    }

    // Default constructor (required for deserialization)
    public Review() {

    }

    public String getText() {
        return text;
    }

    public String getUsername() {
        return username;
    }

    public Book getBook() {
        return book;
    }

    public double getRating() {
        return rating;
    }

    public void editRating(double ratings) {
        this.rating = ratings;
    }

    public void editText(String texts) {
        this.text = texts;
    }

    public String toString() {
        final String p1 = "User" + ':' + ' ';
        final String p2 = "Book" + ':' + ' ';
        final String p3 = "Rating" + ':' + ' ';
        final String p4 = "Review" + ':' + ' ';

        return p1 + username + '\n' + p2 + book.getTitle() + '\n' + p3 + rating + '\n'
                + p4 + '\n' + text;
    }

}
