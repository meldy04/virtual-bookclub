package interface_adapter.my_clubs;

import java.util.HashMap;;
import java.util.Map;

/**
 * The state for my clubs view model.
 */
public class MyClubsState {
    private String currentUsername;
    private Map<String, String> myClubs = new HashMap<>();
    private String errorMessage;

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public Map<String, String> getMyClubs() {
        return myClubs;
    }

    public void setMyClubs(Map<String, String> myClubs) {
        this.myClubs = myClubs;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
