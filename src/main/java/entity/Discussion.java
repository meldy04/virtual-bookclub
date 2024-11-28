package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Discussion {
    private final User user;
    private final Book book;
    private String discussion;
    private final String topic;

    public Discussion(User user, Book book, String topic, String discussion) {
        this.user = user;
        this.book = book;
        this.topic = topic;
        this.discussion = discussion;

    }

    public String getDiscussion () {
        return discussion;
    }

    public User getUser () {
        return user;
    }

    public Book getBook () {
        return book;
    }

    public void editDiscussion (String discussion){
        this.discussion = discussion;
    }

    @Override
    public String toString() {
        return "About this topic" + topic + "in this book" + book + " User " + user +
                " would like to add the following opinion" + discussion;
    }

}
