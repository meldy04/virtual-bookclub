package view;

import java.awt.Component;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import entity.Book;

/**
 * Rendering recommendations book view.
 */
public class BookRenderer extends JLabel implements ListCellRenderer<Book> {
    private Set<Book> wantToReadBooks;

    public BookRenderer(Set<Book> wantToReadBooks) {
        this.wantToReadBooks = wantToReadBooks;
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Book> list, Book book, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        String displayText = book.getTitle() + " by " + book.getAuthor();
        if (wantToReadBooks.contains(book)) {
            // Mark books which have been added to list with a tick
            displayText += " ✔";
        }
        setText(displayText);

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
