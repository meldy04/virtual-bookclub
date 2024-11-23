package use_case.add_message;

/**
 * Input data for the add_message usecase.
 */
public class AddMessageInputData {
    private final String text;

    public AddMessageInputData(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
