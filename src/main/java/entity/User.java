package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A user class.
 */
public class User {

    private final String name;
    private final String password;
    private List<Book> readBooks;
    private List<Book> booksToRead;
    private List<Book> recommendedBooks;
    private List<BookClub> joinedClubs;
    private List<Review> reviews;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.readBooks = new ArrayList<>();
        this.booksToRead = new ArrayList<>();
        this.recommendedBooks = new ArrayList<>();
        this.joinedClubs = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<Book> getReadBooks() {
        return readBooks;
    }

    /**
     * Adds a new book to readBooks list.
     * @param newBook book added to list
     */
    public void addReadBooks(Book newBook) {
        this.readBooks.add(newBook);
    }

    public List<Book> getBooksToRead() {
        return booksToRead;
    }

    /**
     * Adds a new book to booksToRead list.
     * @param newBook book added to the list
     */
    public void addBookToRead(Book newBook) {
        this.booksToRead.add(newBook);
    }

    public List<Book> getRecommendedBooks() {
        return recommendedBooks;
    }

    /**
     * Adds a new book to recommendedBooks list.
     * @param newBook book added to the list
     */
    public void addRecommendedBook(Book newBook) {
        this.recommendedBooks.add(newBook);
    }

    public List<BookClub> getJoinedClubs() {
        return joinedClubs;
    }

    public void setJoinedClubs(List<BookClub> joinedClubs) {
        this.joinedClubs = joinedClubs;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    /**
     * Gets the user's favourite genres based off their read books.
     */
    public Set<String> getFavoriteGenres() {
        final Set<String> genres = new HashSet<>();
        for (Book book : readBooks) {
            genres.add(book.getGenre());
        }
        return genres;
    }

    /**
     * Adds a review for a book that the user has read.
     * @param book The book a user wants to review. Must be a book they have read.
     * @param rating The rating the user has given the book.
     * @param text The body of the user's review.
     * @return The Review object created.
     */
    public Review addReview(Book book, String text, double rating) {
        if (!readBooks.contains(book)) {
            System.out.println("User has not read this book and cannot review it.");
        }

        final Review review = new Review(this, book, text, rating);
        reviews.add(review);
        book.addReview(review);

        return review;
    }

    @Override
    public String toString() {
        return name;
    }
}
