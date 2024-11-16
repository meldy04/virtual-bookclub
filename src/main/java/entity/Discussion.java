package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A discussion class.
 */
public class Discussion {
    private final String topic;
    private List<Message> messages = new ArrayList<>();

    public Discussion(String topic, List<Message> messages) {
        this.topic = topic;
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    @Override
    public String toString() {
        return "Discussion about " + topic;
    }

}
