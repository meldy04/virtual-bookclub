package interface_adapter.my_clubs;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.show_discussions.ShowDiscussionsState;
import interface_adapter.show_discussions.ShowDiscussionsViewModel;
import use_case.my_clubs.MyClubsOutputBoundary;
import use_case.my_clubs.MyClubsOutputData;

/**
 * The presenter for my clubs usecase.
 */
public class MyClubsPresenter implements MyClubsOutputBoundary {
    private final MyClubsViewModel myClubsViewModel;
    private final ShowDiscussionsViewModel showDiscussionsViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public MyClubsPresenter(MyClubsViewModel myClubsViewModel, ShowDiscussionsViewModel showDiscussionsViewModel, LoggedInViewModel loggedInViewModel,
                            ViewManagerModel viewManagerModel) {
        this.myClubsViewModel = myClubsViewModel;
        this.showDiscussionsViewModel = showDiscussionsViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(MyClubsOutputData myClubsOutputData) {
        final MyClubsState myClubsState = myClubsViewModel.getState();
        myClubsState.setMyClubs(myClubsOutputData.getMyClubs());
        myClubsViewModel.setState(myClubsState);
        myClubsViewModel.firePropertyChanged();

        viewManagerModel.setState(myClubsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {
        loggedInViewModel.firePropertyChanged("error");
    }

    @Override
    public void switchToShowDiscussionsView(String currentClub) {
        final ShowDiscussionsState showDiscussionsState = showDiscussionsViewModel.getState();
        showDiscussionsState.setCurrentClub(currentClub);
        showDiscussionsViewModel.setState(showDiscussionsState);
        showDiscussionsViewModel.firePropertyChanged("current club");

        viewManagerModel.setState(showDiscussionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
