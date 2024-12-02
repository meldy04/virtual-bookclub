package interface_adapter.recommendations;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Holds current state of recommendations and updates when state changes.
 */
public class RecommendationViewModel {
    private final PropertyChangeSupport support;
    private RecommendationState state;
    private List<String> recommendations;

    public RecommendationViewModel() {
        this.support = new PropertyChangeSupport(this);
        this.state = new RecommendationState();
        this.recommendations = new ArrayList<>();
    }

    public RecommendationState getState() {
        return state;
    }

    public void setState(RecommendationState newState) {
        final RecommendationState oldState = this.state;
        this.state = newState;
        support.firePropertyChange("state", oldState, newState);
    }

    public List<String> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<String> recommendations) {
        final List<String> oldRecommendations = this.recommendations;
        this.recommendations = recommendations;
        support.firePropertyChange("recommendations", oldRecommendations, recommendations);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
