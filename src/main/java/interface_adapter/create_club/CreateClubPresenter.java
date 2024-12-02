package interface_adapter.create_club;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_message.AddMessageState;
import interface_adapter.add_message.AddMessageViewModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginState;
import use_case.create_club.CreateClubOutputBoundary;
import use_case.create_club.CreateClubOutputData;

public class CreateClubPresenter implements CreateClubOutputBoundary {

    private final CreateClubViewModel createClubViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoggedInViewModel loggedInViewModel;

    public CreateClubPresenter(CreateClubViewModel createClubViewModel,
                               ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel) {
        this.createClubViewModel = createClubViewModel;
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(CreateClubOutputData response) {
//        final LoggedInState loggedInState = loggedInViewModel.getState();
//        final AddMessageState addMessageState = addMessageViewModel.getState();
//
//        loggedInState.setUsername(response.getUsername());
//        addMessageState.setCurrentUsername(response.getUsername());
//
//        this.loggedInViewModel.setState(loggedInState);
//        this.loggedInViewModel.firePropertyChanged();
//        this.addMessageViewModel.setState(addMessageState);

        this.viewManagerModel.setState(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final CreateClubState didNotCreateClub = createClubViewModel.getState();
        didNotCreateClub.setBookclub("");
        didNotCreateClub.setCreated(false);
        this.createClubViewModel.setState(didNotCreateClub);
        createClubViewModel.firePropertyChanged();
    }
}
