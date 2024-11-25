package use_case.add_message;

import java.util.AbstractMap;
import java.util.List;

import data_access.CurrentClubManager;
import data_access.CurrentDiscussionManager;
import data_access.CurrentUsernameManager;

/**
 * The interface of the DAO for the add message usecase.
 */
public interface AddMessageDataAccessInterface extends CurrentUsernameManager, CurrentClubManager,
        CurrentDiscussionManager {
    /**
     * Saves the message of the current user in the current discussion that is in the current book club.
     * @param text text in the message
     */
    void saveMessage(String text);

    /**
     * Gets all messages.
     * @return a list containing a simple entry of username and text.
     */
    List<AbstractMap.SimpleEntry<String, String>> getMessages();

}
