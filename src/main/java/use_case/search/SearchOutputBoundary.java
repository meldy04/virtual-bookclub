package use_case.search;

/**
 * The output boundary for the Search Use Case.
 */
public interface SearchOutputBoundary {
    /**
     * Prepares the success view for the Login Use Case.
     * @param outputData the explanation of the failure
     */
    void prepareSuccessView(SearchOutputData outputData);

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
