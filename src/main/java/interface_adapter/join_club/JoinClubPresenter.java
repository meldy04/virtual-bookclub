package interface_adapter.join_club;

// actuually this should present the join_club bviewthing not the
import interface_adapter.ViewManagerModel;

import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;

import use_case.join_club.JoinClubOutputBoundary;
import use_case.join_club.JoinClubOutputData;
/**
 * The Presenter for the Join Club Use Case.
 */

public class JoinClubPresenter implements JoinClubOutputBoundary {

    private final JoinedClubViewModel joinedClubViewModel;
    private final ViewManagerModel viewManagerModel;

    private final LoggedInViewModel loggedInViewModel;

    public JoinClubPresenter(JoinedClubViewModel joinedClubViewModel,
                             ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.joinedClubViewModel = joinedClubViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(JoinClubOutputData response) {
        final JoinedClubState joinedClubState = joinedClubViewModel.getState();
        joinedClubState.setBookclub(response.getClub());
        this.joinedClubViewModel.setState(joinedClubState);
        this.joinedClubViewModel.firePropertyChanged();
        this.viewManagerModel.setState(joinedClubViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override

    public void prepareFailView(String error) {

        final LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setJoiningError(error);
        loggedInViewModel.firePropertyChanged();

    public void prepareFailView(String message) {
        final JoinClubState didNotJoinClub = joinClubViewModel.getState();
        didNotJoinClub.setBookclub("");
        didNotJoinClub.setJoined(false);
        didNotJoinClub.setErrorMessage(message);
        this.joinClubViewModel.setState(didNotJoinClub);
        joinClubViewModel.firePropertyChanged();

    }
}
