package use_case.add_message;

/**
 * The add message interactor.
 */
public class AddMessageInteractor implements AddMessageInputBoundary {

    private AddMessageDataAccessInterface addMessageDataAccessInterface;
    private AddMessageOutputBoundary addMessageOutputBoundary;

    public AddMessageInteractor(AddMessageDataAccessInterface addMessageDataAccessInterface,
                                AddMessageOutputBoundary addMessageOutputBoundary) {
        this.addMessageDataAccessInterface = addMessageDataAccessInterface;
        this.addMessageOutputBoundary = addMessageOutputBoundary;
    }

    @Override
    public void execute(AddMessageInputData addMessageInputData) {
        final String text = addMessageInputData.getText();

        addMessageDataAccessInterface.saveMessage(text);

        final AddMessageOutputData outputData =
                new AddMessageOutputData(addMessageDataAccessInterface.getMessages(), addMessageDataAccessInterface
                        .getCurrentDiscussion());

        addMessageOutputBoundary.prepareShowMessageView(outputData);
    }

    @Override
    public void refreshMessages() {
        final AddMessageOutputData outputData =
                new AddMessageOutputData(addMessageDataAccessInterface.getMessages(), addMessageDataAccessInterface
                        .getCurrentDiscussion());
        addMessageOutputBoundary.prepareShowMessageView(outputData);
    }

}
