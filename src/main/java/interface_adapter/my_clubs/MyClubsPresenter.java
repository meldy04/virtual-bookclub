package interface_adapter.my_clubs;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.show_notes.ShowNotesState;
import interface_adapter.show_notes.ShowNotesViewModel;
import use_case.my_clubs.MyClubsOutputBoundary;
import use_case.my_clubs.MyClubsOutputData;

/**
 * The presenter for my clubs usecase.
 */
public class MyClubsPresenter implements MyClubsOutputBoundary {
    private final MyClubsViewModel myClubsViewModel;
    private final ShowNotesViewModel showDiscussionsViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public MyClubsPresenter(MyClubsViewModel myClubsViewModel, ShowNotesViewModel showDiscussionsViewModel, LoggedInViewModel loggedInViewModel,
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
        final ShowNotesState showDiscussionsState = showDiscussionsViewModel.getState();
        showDiscussionsState.setCurrentClub(currentClub);
        showDiscussionsViewModel.setState(showDiscussionsState);
        showDiscussionsViewModel.firePropertyChanged("current club");

        viewManagerModel.setState(showDiscussionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
