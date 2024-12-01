package data_access;

/**
 * Manager for the current discussion.
 */
public interface CurrentDiscussionManager {
    /**
     * Sets the current discussion the user wants to open.
     * @param currentDiscussion the discussion the user wants to open
     */
    void setCurrentDiscussion(String currentDiscussion);

    /**
     * Gets the current discussion the user wants to open.
     * @return current discussion.
     */
    String getCurrentDiscussion();
}
