package interface_adapter.add_message;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

/**
 * The State for the add message view model.
 */
public class AddMessageState {
    private String text = "";
    private List<AbstractMap.SimpleEntry<String, String>> messagesList = new ArrayList<>();
    private String currentDiscussion;
    private String currentUsername;

    public void setText(String text) {
        this.text = text;
    }

    public void setMessagesList(List<AbstractMap.SimpleEntry<String, String>> messagesList) {
        this.messagesList = messagesList;
    }

    public void setCurrentDiscussion(String currentDiscussion) {
        this.currentDiscussion = currentDiscussion;
    }

    public String getText() {
        return text;
    }

    public List<AbstractMap.SimpleEntry<String, String>> getMessagesList() {
        return messagesList;
    }

    public String getCurrentDiscussion() {
        return currentDiscussion;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }
}
