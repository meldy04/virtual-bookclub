package use_case.show_topics;

import java.util.List;

/**
 * The show topics interactor.
 */
public class ShowTopicsInteractor implements ShowTopicsInputBoundary {

    private final ShowTopicsOutputBoundary showTopicsOutputBoundary;
    private final ShowTopicsDataAccessInterface showTopicsDataAccessInterface;

    public ShowTopicsInteractor(ShowTopicsOutputBoundary showTopicsOutputBoundary,
                                ShowTopicsDataAccessInterface showTopicsDataAccessInterface) {

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
            final ShowTopicsOutputData outputData = new ShowTopicsOutputData(topics);
            showTopicsOutputBoundary.prepareSuccessView(outputData);
        }

    }
}
