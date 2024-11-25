package use_case.show_discussions;

import java.util.List;

/**
 * The show topics interactor.
 */
public class ShowDiscussionsInteractor implements ShowDiscussionsInputBoundary {

    private final ShowDiscussionsOutputBoundary showTopicsOutputBoundary;
    private final ShowDiscussionsDataAccessInterface showTopicsDataAccessInterface;

    public ShowDiscussionsInteractor(ShowDiscussionsOutputBoundary showTopicsOutputBoundary,
                                     ShowDiscussionsDataAccessInterface showTopicsDataAccessInterface) {

        this.showTopicsOutputBoundary = showTopicsOutputBoundary;
        this.showTopicsDataAccessInterface = showTopicsDataAccessInterface;
    }

    @Override
    public void execute() {
        final List<String> topics = showTopicsDataAccessInterface.getTopics();

        if (topics.isEmpty()) {
            showTopicsOutputBoundary.prepareFailView("There are no discussions here, create a new discussion");
        }

        else {
            final ShowDiscussionsOutputData outputData = new ShowDiscussionsOutputData(topics);
            showTopicsOutputBoundary.prepareSuccessView(outputData);
        }

    }

    @Override
    public void switchToAddMessageView(String discussion) {
        showTopicsOutputBoundary.switchToAddMessageView(discussion);
    }
}
