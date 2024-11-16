package use_case.add_message;

/**
 * The interface of the DAO for the add message usecase.
 */
public interface AddMessageDataAccessInterface {
    /**
     * Saves the message in the current discussion that is in the current book club.
     * @param username username of sender
     * @param text text in the message
     */
    void saveMessage(String username, String text);
}
