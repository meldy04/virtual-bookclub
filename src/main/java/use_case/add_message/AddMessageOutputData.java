package use_case.add_message;

/**
 * Output data for the add message usecase.
 */
public class AddMessageOutputData {
    private String username;
    private String text;
    private boolean useCaseFailed;

    public AddMessageOutputData(String username, String text, boolean useCaseFailed) {
        this.username = username;
        this.text = text;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
