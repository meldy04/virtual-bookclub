package entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a book club with details such as name, genre, members, books, and discussions.
 *
 * @null This object may be null if no book club data is available.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookClub {

    private String name;
    private String genre;
    private List<String> members = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private List<Discussion> discussions = new ArrayList<>();

    // No-argument constructor
    public BookClub() {
    }

    // Parameterized constructor
    public BookClub(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<String> getMembersname() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(List<Discussion> discussions) {
        this.discussions = discussions;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    /**
     * Adds the members to the bookclub.
     *
     * @param username of the user who wants to join.
     */
    public void addMember(String username) {
        this.members.add(username);
    }
    /**
     * Adds the discussions to the bookclub.
     *
     * @param discussion of the user who wants to join.
     */

    public void addDiscussion(Discussion discussion) {
        this.discussions.add(discussion);
    }
    /**
     * Adds the book to the bookclub.
     *
     * @param book of the user who wants to join.
     */

    public void addBook(Book book) {
        this.books.add(book);
    }
}
