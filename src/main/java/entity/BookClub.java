package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class BookClub {

    private String name;
    private String genre;
    private final List<User> members = new ArrayList<>();
    @SuppressWarnings("checkstyle:IllegalType")
    private final HashMap<Book, Review> bookReview = new HashMap<Book, Review>();
    private final List<Discussion> discussions = new ArrayList<Dicsussion>();

    public BookClub(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

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

    /**
     * Adds the a new user to the bookclub.
     * @param user added to the bookclub.
     */

    public void addMember(User user) {
        this.members.add(user);
    }

    /**
     * Adds a new discussions thread to the bookclub.
     * @param discussion added to the bookclub.
     */

    public void addDiscussion(Discussion discussion) {
        this.discussions.add(discussion);
    }

    /**
     * A new bookreview added to the bookclub.
     * @param book a new book review added.
     * @param review coressponding to the book added.
     */

    public void addBookReview(Book book, Review review) {
        this.bookReview.put(book, review);
    }

    public List<User> getMembers() {
        return members;
    }

    public List<Discussion> getDiscussions() {
        return discussions;
    }

    @SuppressWarnings("checkstyle:IllegalType")
    public HashMap getBookReviews() {
        return bookReview;
    }
}