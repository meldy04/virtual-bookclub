package use_case.add_message;

import java.util.AbstractMap;
import java.util.List;

/**
 * Output data for the add message usecase.
 */
public class AddMessageOutputData {
    private final List<AbstractMap.SimpleEntry<String, String>> messages;
    private final String currentTopic;

    public AddMessageOutputData(List<AbstractMap.SimpleEntry<String, String>> messages, String currentTopic) {
        this.messages = messages;
        this.currentTopic = currentTopic;
    }

    public List<AbstractMap.SimpleEntry<String, String>> getMessages() {
        return messages;
    }

}
