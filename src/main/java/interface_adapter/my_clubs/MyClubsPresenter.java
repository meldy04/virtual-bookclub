package interface_adapter.my_clubs;

import interface_adapter.ViewManagerModel;
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
    private final ViewManagerModel viewManagerModel;

    public MyClubsPresenter(MyClubsViewModel myClubsViewModel, ShowDiscussionsViewModel showDiscussionsViewModel,
                            ViewManagerModel viewManagerModel) {
        this.myClubsViewModel = myClubsViewModel;
        this.showDiscussionsViewModel = showDiscussionsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(MyClubsOutputData myClubsOutputData) {
        final MyClubsState myClubsState = myClubsViewModel.getState();
        myClubsState.setMyClubs(myClubsOutputData.getMyClubs());
        myClubsViewModel.setState(myClubsState);
        myClubsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final MyClubsState myClubsState = myClubsViewModel.getState();
        myClubsState.setErrorMessage(errorMessage);
        myClubsViewModel.setState(myClubsState);
        myClubsViewModel.firePropertyChanged("error");
    }

    @Override
    public void switchToShowDiscussionsView(String currentClub) {
        final ShowDiscussionsState showDiscussionsState = showDiscussionsViewModel.getState();
        showDiscussionsState.setCurrentClub(currentClub);
        showDiscussionsViewModel.setState(showDiscussionsState);
        showDiscussionsViewModel.firePropertyChanged();

        viewManagerModel.setState(showDiscussionsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
