package use_case.join_club;

/**
 * The Join Club Interactor.
 */
public class JoinClubInteractor implements JoinClubInputBoundary {

    private final JoinclubOutputBoundary joinclubOutputBoundary;
    private final JoinClubDataAccessInterface clubDataAccessInterface;

    public JoinClubInteractor(JoinclubOutputBoundary joinclubOutputBoundary,
                              JoinClubDataAccessInterface clubDataAccessInterface1) {

        this.joinclubOutputBoundary = joinclubOutputBoundary;
        this.clubDataAccessInterface = clubDataAccessInterface1;
    }

    @Override
    public void execute(JoinClubInputData joinClubInputData) {
        final String username = joinClubInputData.getUsername();
        final String clubName = joinClubInputData.getClubName();

        if (clubDataAccessInterface.isMember(username, clubName)) {
            joinclubOutputBoundary.prepareFailView();
        }
        else {
            clubDataAccessInterface.addUser(username, clubName);
            final JoinClubOutputData outputData = new JoinClubOutputData(username, clubName, false);
            joinclubOutputBoundary.prepareSuccessView(outputData);
        }

    }
}
