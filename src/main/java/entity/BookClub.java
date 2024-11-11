package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"checkstyle:WriteTag", "checkstyle:SuppressWarnings"})
public class BookClub {

    private String name;
    private String genre;
    private final List<User> members = new ArrayList<>();
    @SuppressWarnings("checkstyle:IllegalType")
    private final Map<Book, Review> bookReview = new HashMap<Book, Review>();
    private final List<Discussion> discussions = new ArrayList<Discussion>();

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
     * Adds the a new user to the book club.
     * @param user added to the book club.
     */

    public void addMember(User user) {
        this.members.add(user);
    }

    /**
     * Adds a new discussions thread to the book club.
     * @param discussion added to the book club.
     */

    public void addDiscussion(Discussion discussion) {
        this.discussions.add(discussion);
    }

    /**
     * A new book-review added to the book club.
     * @param book a new book review added.
     * @param review corresponding to the book added.
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
    public Map<Book, Review> getBookReviews() {
        return bookReview;
    }
}
