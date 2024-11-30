package use_case.show_discussions;

import java.util.List;

/**
 * DAO for show discussions usecase.
 */

public interface ShowDiscussionsDataAccessInterface {
    /**
     * Gets the topics of the discussions in the current club.
     * @return list of topic names
     */
    List<String> getDiscussionsTopics();

    /**
     * Sets the current discussion topic in the current club.
     * @param discussion the discussion topic
     */
    void setCurrentDiscussion(String discussion);
}
