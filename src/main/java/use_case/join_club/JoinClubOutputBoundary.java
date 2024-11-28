package use_case.join_club;

/**
 * Output Boundary for Join Club Use case.
 */
public interface JoinClubOutputBoundary {
    /**
     * Prepares the success view for the Join Club Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(JoinClubOutputData outputData);

    /**
     * Prepares the fail view for the Join Club Use Case.
     */
    void prepareFailView(String message);

}
