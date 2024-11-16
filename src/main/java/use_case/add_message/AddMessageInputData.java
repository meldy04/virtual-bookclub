package use_case.add_message;

/**
 * Input data for the add_message usecase.
 */
public class AddMessageInputData {
    private final String username;
    private final String text;

    public AddMessageInputData(String username, String text) {
        this.username = username;
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }
}
