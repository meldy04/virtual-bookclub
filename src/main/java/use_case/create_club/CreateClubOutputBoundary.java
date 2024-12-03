package use_case.create_club;

import use_case.create_club.CreateClubOutputData;

public interface CreateClubOutputBoundary {

    void prepareSuccessView(CreateClubOutputData outputData);

    /**
     * Prepares the fail view for the Join Club Use Case.
     */
    void prepareFailView(String errorMessage);

    void switchToLoggedInView();
}
