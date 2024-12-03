

package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_message.AddMessageState;
import interface_adapter.add_message.AddMessageViewModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.join_club.JoinClubState;
import interface_adapter.join_club.JoinClubViewModel;
import interface_adapter.my_clubs.MyClubsState;
import interface_adapter.my_clubs.MyClubsViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search.SearchedState;
import interface_adapter.search.SearchedViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final JoinClubViewModel joinClubViewModel;
    private final AddMessageViewModel addMessageViewModel;
    private final MyClubsViewModel myClubsViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel, JoinClubViewModel joinClubViewModel,
                          AddMessageViewModel addMessageViewModel, MyClubsViewModel myClubsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.joinClubViewModel = joinClubViewModel;
        this.addMessageViewModel = addMessageViewModel;
        this.myClubsViewModel = myClubsViewModel;

    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        final LoggedInState loggedInState = loggedInViewModel.getState();
        final JoinClubState joinClubState = joinClubViewModel.getState();
        final AddMessageState addMessageState = addMessageViewModel.getState();
        final MyClubsState myClubsState = myClubsViewModel.getState();

        addMessageState.setCurrentUsername(response.getUsername());
        joinClubState.setUsername(response.getUsername());
        loggedInState.setUsername(response.getUsername());
        myClubsState.setCurrentUsername(response.getUsername());

        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();
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
