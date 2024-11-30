package use_case.show_discussions;

import java.util.List;

/**
 * The show discussions interactor.
 */
public class ShowDiscussionsInteractor implements ShowDiscussionsInputBoundary {

    private final ShowDiscussionsOutputBoundary showTopicsOutputBoundary;
    private final ShowDiscussionsDataAccessInterface showTopicsDataAccessInterface;

    public ShowDiscussionsInteractor(ShowDiscussionsDataAccessInterface showTopicsDataAccessInterface,
                                     ShowDiscussionsOutputBoundary showTopicsOutputBoundary) {

        this.showTopicsOutputBoundary = showTopicsOutputBoundary;
        this.showTopicsDataAccessInterface = showTopicsDataAccessInterface;
    }

    @Override
    public void execute() {
        final List<String> topics = showTopicsDataAccessInterface.getDiscussionsTopics();

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
        showTopicsDataAccessInterface.setCurrentDiscussion(discussion);
        showTopicsOutputBoundary.switchToAddMessageView(discussion);
    }
}
