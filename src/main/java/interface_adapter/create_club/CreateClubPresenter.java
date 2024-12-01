package interface_adapter.create_club;

import interface_adapter.ViewManagerModel;
import use_case.create_club.CreateClubOutputBoundary;
import use_case.create_club.CreateClubOutputData;

public class CreateClubPresenter implements CreateClubOutputBoundary {

    private final CreateClubViewModel createClubViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreateClubPresenter(CreateClubViewModel createClubViewModel,
                               ViewManagerModel viewManagerModel) {
        this.createClubViewModel = createClubViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreateClubOutputData response) {
        final CreateClubState createclubState = createClubViewModel.getState();
        createclubState.setBookclub(response.getClubname());
        createclubState.setCreated(true);
        this.createClubViewModel.setState(createclubState);
        this.createClubViewModel.firePropertyChanged();

        this.viewManagerModel.setState(createClubViewModel.getViewName());
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
