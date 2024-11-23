package use_case.show_topics;

import java.util.ArrayList;
import java.util.List;

/**
 * The output data for the show topics usecase.
 */
public class ShowTopicsOutputData {

    private List<String> topics = new ArrayList<>();

    public ShowTopicsOutputData(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getTopics() {
        return topics;
    }
}

