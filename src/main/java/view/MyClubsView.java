package view;

import interface_adapter.my_clubs.MyClubsController;
import interface_adapter.my_clubs.MyClubsState;
import interface_adapter.my_clubs.MyClubsViewModel;
import interface_adapter.show_discussions.ShowDiscussionsViewModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

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

    public MyClubsView(MyClubsViewModel myClubsViewModel) {
        this.myClubsViewModel = myClubsViewModel;
        myClubsViewModel.addPropertyChangeListener(this);

        title = new JLabel(ShowDiscussionsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final String[] columnNames = {"Club Name", "Description"};
        final DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        final Map<String, String> clubs = myClubsViewModel.getState().getMyClubs();

        for (Map.Entry<String, String> entry : clubs.entrySet()) {
            // adds entry of name and description in the table
            tableModel.addRow(new String[]{entry.getKey(), entry.getValue()});
        }
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
                        MyClubsState currentState = new MyClubsViewModel().getState();

                    }
                }

            }
        });

        discussions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void setMyClubsController(MyClubsController myClubsController) {
        this.myClubsController = myClubsController;
    }
}
