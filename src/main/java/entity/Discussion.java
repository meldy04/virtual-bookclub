package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A discussion class.
 */
public class Discussion {
    private String topic;
    private List<Message> messages = new ArrayList<>();

    public Discussion(String topic, List<Message> messages) {
        this.topic = topic;
        this.messages = messages;
    }

    // Default constructor (required for deserialization)
    public Discussion() {

    }

    public Discussion(String topic) {
        this.topic = topic;
    }

    public Discussion(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    /**
     * Adds new message.
     * @param newMessage to be added
     */
    public void addMessage(Message newMessage) {
        this.messages.add(newMessage);
    }

    @Override
    public String toString() {
        return "Discussion about " + topic;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
