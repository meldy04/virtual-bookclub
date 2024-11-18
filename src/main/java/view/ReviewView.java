package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Review;

public class ReviewView extends AbstractTableModel {

    private final List<Review> reviews;
    private final String[] columns = {"User", "Book", "Rating", "Review"};

    public ReviewView(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public int getRowCount() {
        return reviews.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final Review review = reviews.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return review.getUser().getName();
            case 1:
                return review.getBook().getTitle();
            case 2:
                return review.getRating();
            case 3:
                return review.getText();
            default:
                return null;

        }
    }
}
