package entity;

/**
 * A representation of the reviews.
 */

public class
Review implements Reviews {

    private User user;
    private Book book;
    private String text;
    private double rating;

    public Review(User user, Book book, String text, double rating) {
        this.user = user;
        this.book = book;
        this.text = text;
        this.rating = rating;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public Book getBook() {
        return book;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void editRating(double ratings) {
        this.rating = ratings;
    }

    @Override
    public void editText(String texts) {
        this.text = texts;
    }

    @Override
    public String toString() {
        final String p1 = "User" + ':' + ' ';
        final String p2 = "Book" + ':' + ' ';
        final String p3 = "Rating" + ':' + ' ';
        final String p4 = "Review" + ':' + ' ';

        return p1 + user.getName() + '\n' + p2 + book.getTitle() + '\n' + p3 + rating + '\n'
                + p4 + '\n' + text;
    }

}
