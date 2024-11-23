package entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookClub {

    private String name;
    private String genre;
    private List<User> members = new ArrayList<>();
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

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
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

    // Utility methods
    public void addMember(User user) {
        this.members.add(user);
    }

    public void addDiscussion(Discussion discussion) {
        this.discussions.add(discussion);
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}
