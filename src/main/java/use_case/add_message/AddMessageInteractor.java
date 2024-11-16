package use_case.add_message;

/**
 * The add message interactor.
 */
public class AddMessageInteractor implements AddMessageInputBoundary {

    private AddMessageDataAccessInterface addMessageDataAccessInterface;
    private AddMessageOutputBoundary addMessageOutputBoundary;

    @Override
    public void execute(AddMessageInputData addMessageInputData) {
        final String username = addMessageInputData.getUsername();
        final String text = addMessageInputData.getText();

        addMessageDataAccessInterface.saveMessage(username, text);

        final AddMessageOutputData outputData = new AddMessageOutputData(username, text);

        addMessageOutputBoundary.prepareShowMessageView(outputData);
    }
}
