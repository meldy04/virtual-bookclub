package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Model for the View Manager. Its state is the name of the View which
 * is currently active. An initial state of "" is used.
 */
public class ViewManagerModel extends ViewModel<String> {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public ViewManagerModel() {
        super("view manager");
        this.setState("");
    }

    /**
     * Sets a new state for the ViewManagerModel, notifying listeners.
     * @param newState the name of the new view to switch to
     */
    @Override
    public void setState(String newState) {
        String oldState = this.state;
        this.state = newState;
        System.out.println("ViewManagerModel: State changed from " + oldState + " to " + newState);
        pcs.firePropertyChange("state", oldState, newState);
    }

    /**
     * Adds a property change listener to this model.
     * @param listener the listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener from this model.
     * @param listener the listener to remove
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}
