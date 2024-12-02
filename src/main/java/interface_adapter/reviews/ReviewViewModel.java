package interface_adapter.reviews;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages review state for UI updates.
 */
public class ReviewViewModel {
    private final PropertyChangeSupport support;
    private List<String> reviews;

    public ReviewViewModel() {
        this.support = new PropertyChangeSupport(this);
        this.reviews = new ArrayList<>();
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void setReviews(List<String> reviews) {
        final List<String> oldReviews = this.reviews;
        this.reviews = reviews;
        support.firePropertyChange("reviews", oldReviews, reviews);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}