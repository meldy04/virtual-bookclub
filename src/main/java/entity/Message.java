package entity;

/**
 * A Message class.
 */

public class Message {
    private final String username;
    private final String text;

    public Message(String username, String text) {
        this.username = username;
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Message from" + getUsername() + ":" + text;
    }

}
