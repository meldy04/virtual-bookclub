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
}
