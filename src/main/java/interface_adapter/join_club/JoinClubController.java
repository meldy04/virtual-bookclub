package interface_adapter.join_club;

import entity.User;
import use_case.join_club.JoinClubInputBoundary;
import use_case.join_club.JoinClubInputData;

/**
 * Controller for the Join Club Use Case.
 */
public class JoinClubController {
    private final JoinClubInputBoundary joinClubUseCaseInteractor;

    public JoinClubController(JoinClubInputBoundary joinClubUseCaseInteractor) {
        this.joinClubUseCaseInteractor = joinClubUseCaseInteractor;
    }

    /**
     * Executes the Join Club Use Case.
     * @param user username of the user that wants to join
     * @param clubName name of the club the user wants to join
     */
    public void execute(User user, String clubName) {
        final JoinClubInputData inputData = new JoinClubInputData(user, clubName);
        joinClubUseCaseInteractor.execute(inputData);

    }

}
