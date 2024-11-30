package use_case.add_message;

import java.util.AbstractMap;
import java.util.List;

/**
 * The interface of the DAO for the add message usecase.
 */
public interface AddMessageDataAccessInterface {
    /**
     * Saves the message of the current user in the current discussion that is in the current book club.
     * @param text text in the message
     */
    void saveMessage(String text);

    /**
     * Returns the name of the book club the user opened the discussions for.
     * @return name of the book club
     */
    String getCurrentClub();

    /**
     * Returns the topic of the discussion the user views messages for.
     * @return name of the topic
     */
    String getCurrentDiscussion();

    // the last two methods should probably be in the data access interface related to discussions as that is where we
    // would have access to the currentClub and currentDiscussion

    /**
     * Returns the username of the current user.
     * @return username of the current user
     */
    String getCurrentUsername();

    /**
     * Gets all messages.
     * @return a list containing a simple entry of username and text.
     */
    List<AbstractMap.SimpleEntry<String, String>> getMessages();

    void setCurrentDiscussion(String topic);

    void setCurrentClub(String clubName);

    void setCurrentUsername(String currentUsername);
}
