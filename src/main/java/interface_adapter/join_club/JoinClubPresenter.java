package interface_adapter.join_club;

import interface_adapter.ViewManagerModel;
import use_case.join_club.JoinClubInputBoundary;
import use_case.join_club.JoinClubOutputBoundary;
import use_case.join_club.JoinClubOutputData;
/**
 * The Presenter for the Join Club Use Case.
 */

public class JoinClubPresenter implements JoinClubOutputBoundary {

    private final JoinClubViewModel joinClubViewModel;
    private final ViewManagerModel viewManagerModel;

    public JoinClubPresenter(JoinClubViewModel joinClubViewModel,
                             ViewManagerModel viewManagerModel) {
        this.joinClubViewModel = joinClubViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(JoinClubOutputData response) {
        final JoinClubState joinclubState = joinClubViewModel.getState();
        joinclubState.setBookclub(response.getClubName());
        joinclubState.setJoined(true);
        this.joinClubViewModel.setState(joinclubState);
        this.joinClubViewModel.firePropertyChanged();

        this.viewManagerModel.setState(joinClubViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String message) {
        final JoinClubState didNotJoinClub = joinClubViewModel.getState();
        didNotJoinClub.setBookclub("");
        didNotJoinClub.setJoined(false);
        didNotJoinClub.setErrorMessage(message);
        this.joinClubViewModel.setState(didNotJoinClub);
        joinClubViewModel.firePropertyChanged();
    }
}
