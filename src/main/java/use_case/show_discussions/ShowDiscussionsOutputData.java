package use_case.show_discussions;

import java.util.ArrayList;
import java.util.List;

/**
 * The output data for the show topics usecase.
 */
public class ShowDiscussionsOutputData {

    private List<String> topics = new ArrayList<>();

    public ShowDiscussionsOutputData(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getTopics() {
        return topics;
    }
}

