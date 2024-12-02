package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing a book club.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookClub {

    private String name;
    private String description;
    private List<String> members = new ArrayList<>();
    private List<Book> books = new ArrayList<>();
    private Map<String, Discussion> discussions = new HashMap<>();

    // No-argument constructor
    public BookClub() {
    }

    // Parameterized constructor
    public BookClub(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    /**
     * Returns true if the username is in members, false otherwise.
     * @param username the username
     * @return if members contains username
     */
    public Boolean isMember(String username) {
        return members.contains(username);
    }

    /**
     * Removes userName from the list.
     * @param userName to removed.
     */
    public void removeMember(String userName) {
        this.members.remove(userName);
    }
}
