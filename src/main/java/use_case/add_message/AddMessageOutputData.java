package use_case.add_message;

/**
 * Output data for the add message usecase.
 */
public class AddMessageOutputData {
    private String username;
    private String text;

    public AddMessageOutputData(String username, String text) {
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
