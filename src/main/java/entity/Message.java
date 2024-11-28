package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A simple implementation of the Message interface.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
