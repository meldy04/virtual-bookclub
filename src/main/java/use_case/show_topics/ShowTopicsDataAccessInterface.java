package use_case.show_topics;

import java.util.List;

/**
 * DAO for show topics usecase.
 */

public interface ShowTopicsDataAccessInterface {
    /**
     * Gets the topics of the discussions in the current club.
     * @return list of topic names
     */
    List<String> getTopics();
}
