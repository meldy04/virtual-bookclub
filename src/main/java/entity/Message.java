package entity;

/**
 * A simple implementation of the Messages interface.
 */

public class Message {
    private final User username;
    private final String text;

    public Message(User username, String text) {
        this.username = username;
        this.text = text;
    }

    public User getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

}
