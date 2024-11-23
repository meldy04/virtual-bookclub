package use_case.show_topics;

/**
 * Output boundary for show topics usecase.
 */
public interface ShowTopicsOutputBoundary {

    /**
     * Prepares the success view for the show topics usecase.
     * @param outputData the output data
     */
    void prepareSuccessView(ShowTopicsOutputData outputData);

    /**
     * Prepares the fail view for the show topics usecase.
     * @param errorMessage the message that will be displayed
     */
    void prepareFailView(String errorMessage);
}
