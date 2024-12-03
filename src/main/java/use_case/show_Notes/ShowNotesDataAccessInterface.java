package use_case.show_Notes;

import java.util.List;

/**
 * DAO for show discussions usecase.
 */

public interface ShowNotesDataAccessInterface {
    /**
     * Gets the topics of the discussions in the current club.
     * @return list of topic names
     */
    List<String> getNotesTopics();

    /**
     * Sets the current Note topic in the current club.
     * @param discussion the discussion topic
     */
    void setCurrentNote(String discussion);
}
