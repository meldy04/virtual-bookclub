package use_case.join_club;

import entity.User;

/**
 * The Join Club Interactor.
 */
public class JoinClubInteractor implements JoinClubInputBoundary {

    private final JoinClubOutputBoundary joinclubOutputBoundary;
    private final JoinClubDataAccessInterface clubDataAccessInterface;

    public JoinClubInteractor(JoinClubOutputBoundary joinClubOutputBoundary,
                              JoinClubDataAccessInterface clubDataAccessInterface1) {

        this.joinclubOutputBoundary = joinClubOutputBoundary;
        this.clubDataAccessInterface = clubDataAccessInterface1;
    }

    @Override
    public void execute(JoinClubInputData joinClubInputData) {
        final User user  = joinClubInputData.getUser();
        final String clubName = joinClubInputData.getClubName();


        if (clubDataAccessInterface.isMember(user, clubName)) {
            joinclubOutputBoundary.prepareFailView();
        }
        else {
            clubDataAccessInterface.addUser(user, clubName);
            final JoinClubOutputData outputData = new JoinClubOutputData(user, clubName,false);
            joinclubOutputBoundary.prepareSuccessView(outputData);
        }

    }
}
