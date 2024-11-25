package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import data_access.InMemoryBookClubDataAccessObject;
import entity.BookClub;
import entity.Discussion;
import entity.Message;
import interface_adapter.add_message.AddMessageController;
import interface_adapter.add_message.AddMessagePresenter;
import interface_adapter.add_message.AddMessageState;
import interface_adapter.add_message.AddMessageViewModel;
import use_case.add_message.AddMessageDataAccessInterface;
import use_case.add_message.AddMessageInputBoundary;
import use_case.add_message.AddMessageInteractor;
import use_case.add_message.AddMessageOutputBoundary;

/**
 * The View for when the user is adding/viewing messages.
 */

public class AddMessageView extends JPanel implements PropertyChangeListener {
    private final String viewName = "add message";

    private final AddMessageViewModel addMessageViewModel;
    private final JTextField messageInputField = new JTextField(25);
    private AddMessageController addMessageController;
    private JTextArea messagesArea = new JTextArea(25, 25);
    private final JLabel title;
    private final JButton post;

    public AddMessageView(AddMessageViewModel addMessageViewModel) {
        this.addMessageViewModel = addMessageViewModel;
        addMessageViewModel.addPropertyChangeListener(this);

        title = new JLabel(AddMessageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        post = new JButton(AddMessageViewModel.POST_BUTTON_LABEL);

        final JPanel textboxPanel = new JPanel();
        textboxPanel.add(messageInputField);
        textboxPanel.add(post);

        messagesArea.setLineWrap(true);
        messagesArea.setWrapStyleWord(true);
        messagesArea.setEditable(false);
        messagesArea.setFont(new Font("Arial", Font.PLAIN, 20));
        final JScrollPane scrollPane = new JScrollPane(messagesArea);
        final JPanel messagesPanel = new JPanel();
        messagesPanel.add(scrollPane);

        post.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(post)) {
                            final AddMessageState currentState = addMessageViewModel.getState();
                            addMessageController.execute(currentState.getText());
                            messageInputField.setText("");
                        }
                    }
                }
        );

        final Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This code runs every 2 seconds
                addMessageController.showMessages(addMessageViewModel.getState().getCurrentDiscussion());
            }
        });

        timer.start();
        addMessageListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(messagesPanel);
        this.add(textboxPanel);
    }

    private void addMessageListener() {
        messageInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final AddMessageState currentState = addMessageViewModel.getState();
                currentState.setText(messageInputField.getText());
                addMessageViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final AddMessageState state = (AddMessageState) evt.getNewValue();
        final List<AbstractMap.SimpleEntry<String, String>> messagesList = state.getMessagesList();
        messagesArea.setText("");
        for (AbstractMap.SimpleEntry<String, String> entry: messagesList) {
            final String username = entry.getKey();
            final String text = entry.getValue();
            messagesArea.append(username + ": " + text + "\n");
        }
        title.setText(AddMessageViewModel.TITLE_LABEL + addMessageViewModel.getState().getCurrentDiscussion());

    }

    public String getViewName() {
        return viewName;
    }

    public void setSignupController(AddMessageController controller) {
        this.addMessageController = controller;
    }

//    psvm for testing (will remove later)
//    public static void main(String[] args) {
//        final AddMessageViewModel model = new AddMessageViewModel();
//
//        Map<String, BookClub> bookClubMap = new HashMap<>();
//        BookClub club = new BookClub("Cooking", "Culinary");
//        List<Message> messages = new ArrayList<>();
//        messages.add(new Message("user 1", "hello"));
//        club.addDiscussion("topic 1", new Discussion("topic 1", messages));
//        bookClubMap.put("Cooking", club);
//
//        final AddMessageDataAccessInterface dataAccessObject = new InMemoryBookClubDataAccessObject(bookClubMap);
//        dataAccessObject.setCurrentDiscussion("topic 1");
//        dataAccessObject.setCurrentClub("Cooking");
//        dataAccessObject.setCurrentUsername("user 1");
//        final AddMessageOutputBoundary presenter = new AddMessagePresenter(model);
//        final AddMessageInputBoundary interactor = new AddMessageInteractor(dataAccessObject, presenter);
//        final AddMessageController controller = new AddMessageController(interactor); // Create the controller
//
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Add Message View");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            AddMessageView view = new AddMessageView(model);
//            view.setSignupController(controller); // Assign the controller to the view
//
//            frame.add(view); // Add the JPanel to the JFrame
//            frame.setSize(500, 400); // Set the size of the JFrame
//            frame.setVisible(true); // Make the JFrame visible
//        });
//        }

}
