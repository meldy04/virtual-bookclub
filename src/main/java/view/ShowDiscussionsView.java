package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import interface_adapter.show_discussions.ShowDiscussionsController;
import interface_adapter.show_discussions.ShowDiscussionsState;
import interface_adapter.show_discussions.ShowDiscussionsViewModel;

/**
 * The View for the Show Topics Use Case.
 */
public class ShowDiscussionsView extends JPanel implements PropertyChangeListener {
    private final String viewName = "show discussions";

    private final ShowDiscussionsViewModel showTopicsViewModel;
    private ShowDiscussionsController showDiscussionsController;

    private final JList<String> topicsList = new JList<>();
    private final JLabel title;
    private final JButton viewMessages;
    private final JButton newDiscussion;

    public ShowDiscussionsView(ShowDiscussionsViewModel showTopicsViewModel) {
        this.showTopicsViewModel = showTopicsViewModel;
        showTopicsViewModel.addPropertyChangeListener(this);

        title = new JLabel(ShowDiscussionsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        topicsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final JScrollPane scrollPane = new JScrollPane(topicsList);

        viewMessages = new JButton(ShowDiscussionsViewModel.VIEW_MESSAGES_BUTTON_LABEL);
        newDiscussion = new JButton(ShowDiscussionsViewModel.NEW_DISCUSSION_BUTTON_LABEL);

        topicsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) {
                    final String selectedTopic = topicsList.getSelectedValue();
                    final ShowDiscussionsState currentState = showTopicsViewModel.getState();
                    currentState.setCurrentDiscussion(selectedTopic);
                    showTopicsViewModel.setState(currentState);

                }
            }
        });

        viewMessages.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        final String currentDiscussion = showTopicsViewModel.getState().getCurrentDiscussion();
                        showDiscussionsController.switchToAddMessageView(currentDiscussion);
                    }
                }
        );

        final JPanel buttons = new JPanel();
        buttons.add(viewMessages);
        buttons.add(newDiscussion);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(scrollPane);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals("current club")) {
            // corresponds to the switch to show discussions view
            showDiscussionsController.execute();
        }
        else if (evt.getPropertyName().equals("error")) {
            final ShowDiscussionsState state = (ShowDiscussionsState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        }

        else {
            final ShowDiscussionsState state = (ShowDiscussionsState) evt.getNewValue();
            topicsList.setListData(state.getTopics().toArray(new String[0]));
            title.setText(ShowDiscussionsViewModel.TITLE_LABEL + showTopicsViewModel.getState().getCurrentClub());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setShowDiscussionsController(ShowDiscussionsController controller) {
        this.showDiscussionsController = controller;
    }
}
