package entity;

/**
 * A Message class.
 */

public class Message {
    private final User user;
    private final String text;

    public Message(User user, String text) {
        this.user = user;
        this.text = text;
    }

    public String getUsername() {
        return user.getName();
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Message from" + getUsername() + ":" + text;
    }

}
