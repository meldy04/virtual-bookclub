package use_case.add_message;

/**
 * Input Boundary for actions related to adding a message.
 */
public interface AddMessageInputBoundary {
    /**
     * Executes the add_message usecase (adds message to discussion).
     * @param addMessageInputData the input data
     */
    void execute(AddMessageInputData addMessageInputData);
}
