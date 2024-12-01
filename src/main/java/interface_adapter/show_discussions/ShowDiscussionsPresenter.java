package interface_adapter.show_discussions;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_message.AddMessageState;
import interface_adapter.add_message.AddMessageViewModel;
import use_case.show_discussions.ShowDiscussionsOutputBoundary;
import use_case.show_discussions.ShowDiscussionsOutputData;

/**
 * The presenter for the show topics usecase.
 */
public class ShowDiscussionsPresenter implements ShowDiscussionsOutputBoundary {

    private final ShowDiscussionsViewModel showDiscussionsViewModel;
    private final AddMessageViewModel addMessageViewModel;
    private final ViewManagerModel viewManagerModel;

    public ShowDiscussionsPresenter(ShowDiscussionsViewModel showDiscussionsViewModel,
                                    ViewManagerModel viewManagerModel, AddMessageViewModel addMessageViewModel) {
        this.showDiscussionsViewModel = showDiscussionsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.addMessageViewModel = addMessageViewModel;
    }

    @Override
    public void prepareSuccessView(ShowDiscussionsOutputData outputData) {
        final ShowDiscussionsState state = showDiscussionsViewModel.getState();
        state.setTopics(outputData.getTopics());
        showDiscussionsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ShowDiscussionsState state = showDiscussionsViewModel.getState();
        state.setErrorMessage(errorMessage);
        showDiscussionsViewModel.firePropertyChanged("error");
    }

    @Override
    public void switchToAddMessageView(String discussion) {
        final AddMessageState state = addMessageViewModel.getState();
        state.setCurrentDiscussion(discussion);
        addMessageViewModel.firePropertyChanged();

        viewManagerModel.setState(addMessageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

}
