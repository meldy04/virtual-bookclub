package use_case.show_discussions;

/**
 * Input boundary for actions related to showing discussion topics of the current book club.
 */
public interface ShowDiscussionsInputBoundary {
    /**
     * Executes the show topics use case.
     */
    void execute();

    /**
     * Switch to add message view.
     * @param discussion the discussion chosen by the user
     */
    void switchToAddMessageView(String discussion);
}
