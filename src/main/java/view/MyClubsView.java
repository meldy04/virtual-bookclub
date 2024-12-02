package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import interface_adapter.my_clubs.MyClubsController;
import interface_adapter.my_clubs.MyClubsState;
import interface_adapter.my_clubs.MyClubsViewModel;

/**
 * The view for my clubs use case.
 */
public class MyClubsView extends JPanel implements PropertyChangeListener {
    private final String viewName = "my clubs";

    private final MyClubsViewModel myClubsViewModel;
    private MyClubsController myClubsController;

    private final JLabel title;
    private final JButton discussions;
    private final JButton books;

    private final JTable myClubs;
    private final String[] columnNames = {"Club Name", "Description"};
    private final DefaultTableModel tableModel;

    public MyClubsView(MyClubsViewModel myClubsViewModel) {
        this.myClubsViewModel = myClubsViewModel;
        myClubsViewModel.addPropertyChangeListener(this);

        title = new JLabel(myClubsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        tableModel = new DefaultTableModel(columnNames, 0);
        myClubs = new JTable(tableModel);
        myClubs.setCellSelectionEnabled(true);
        myClubs.setRowSelectionAllowed(false);
        myClubs.setColumnSelectionAllowed(false);
        discussions = new JButton(MyClubsViewModel.DISCUSSIONS_LABEL);
        books = new JButton(MyClubsViewModel.BOOKS_LABEL);

        myClubs.getColumnModel().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {

                if (!evt.getValueIsAdjusting()) {
                    final int selectedColumn = myClubs.getSelectedColumn();
                    if (selectedColumn == 1) {
                        myClubs.clearSelection();
                    }
                    else {
                        final int selectedRow = myClubs.getSelectedRow();
                        final String selectedClub = (String) myClubs.getValueAt(selectedRow, selectedColumn);
                        final MyClubsState myClubsState = myClubsViewModel.getState();
                        myClubsState.setCurrentClub(selectedClub);
                        myClubsViewModel.setState(myClubsState);
                    }
                }

            }
        });

        discussions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                final String currentClub = myClubsViewModel.getState().getCurrentClub();
                myClubsController.switchToShowMessageView(currentClub);
            }
        });

        final JScrollPane scrollPane = new JScrollPane(myClubs);
        final JPanel buttons = new JPanel();
        buttons.add(discussions);
        buttons.add(books);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(scrollPane);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final MyClubsState state = (MyClubsState) evt.getNewValue();
        final Map<String, String> newClubs = state.getMyClubs();
        // clears table
        tableModel.setRowCount(0);
        for (Map.Entry<String, String> entry : newClubs.entrySet()) {
            // adds entry of name and description in the table
            tableModel.addRow(new String[]{entry.getKey(), entry.getValue()});
        }
    }

    public void setMyClubsController(MyClubsController myClubsController) {
        this.myClubsController = myClubsController;
    }

    public String getViewName() {
        return viewName;
    }
}
