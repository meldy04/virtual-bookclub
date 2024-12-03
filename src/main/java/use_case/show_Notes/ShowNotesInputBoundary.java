package use_case.show_Notes;

/**
 * Input boundary for actions related to showing discussion topics of the current book club.
 */
public interface ShowNotesInputBoundary {
    /**
     * Executes the show discussions use case.
     */
    void execute();

    /**
     * Switch to add message view.
     * @param discussion the discussion chosen by the user
     */
    void switchToAddMessageView(String discussion);
}
