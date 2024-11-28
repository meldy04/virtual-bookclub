package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_message.AddMessageState;
import interface_adapter.add_message.AddMessageViewModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.join_club.JoinClubState;
import interface_adapter.join_club.JoinClubViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final AddMessageViewModel addMessageViewModel;
    private final ViewManagerModel viewManagerModel;

    private final JoinClubViewModel joinClubViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,

                          LoginViewModel loginViewModel, JoinClubViewModel joinClubViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.joinClubViewModel = joinClubViewModel;

                          LoginViewModel loginViewModel, AddMessageViewModel addMessageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.addMessageViewModel = addMessageViewModel;

    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final LoggedInState loggedInState = loggedInViewModel.getState();

        final JoinClubState joinClubState = joinClubViewModel.getState();
        joinClubState.setUsername(response.getUsername());

        final AddMessageState addMessageState = addMessageViewModel.getState();


        loggedInState.setUsername(response.getUsername());
        addMessageState.setCurrentUsername(response.getUsername());

        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();
        // sets current username for add message state
        this.addMessageViewModel.setState(addMessageState);

        this.viewManagerModel.setState(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
