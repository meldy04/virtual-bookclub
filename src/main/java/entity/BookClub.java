package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing a book club.
 */
public class BookClub {

    private String name;
    private String genre;
    private List<String> members = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private Map<String, Discussion> discussions = new HashMap<>();

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

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public Map<String, Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(Map<String, Discussion> discussions) {
        this.discussions = discussions;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    /**
     * Adds username to members.
     * @param username to be added to members
     */
    public void addMember(String username) {
        this.members.add(username);
    }

    /**
     * Adds a new discussion to discussions.
     * @param topic of the discussion
     * @param discussion to be added
     */
    public void addDiscussion(String topic, Discussion discussion) {
        this.discussions.put(topic, discussion);
    }

    /**
     * Gets the discussion with corresponding topic.
     * @param topic the topic of the discussion
     * @return a discussion
     */
    public Discussion getDiscussion(String topic) {
        return this.discussions.get(topic);
    }

    /**
     * Adds a new book to books.
     * @param book to be added
     */
    public void addBook(Book book) {
        this.books.add(book);
    }
}
