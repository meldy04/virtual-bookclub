package view;

import java.awt.Component;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import interface_adapter.reviews.ReviewViewModel;

public class ReviewView extends JPanel implements PropertyChangeListener {
    private final ReviewViewModel reviewViewModel;
    private final JTextArea reviewsArea = new JTextArea(25, 25);
    private final JTextField newReviewInputField = new JTextField(25);
    private final JButton addReview;
    private final JButton refreshReviews;

    public ReviewView(ReviewViewModel reviewViewModel) {
        this.reviewViewModel = reviewViewModel;
        reviewViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Reviews");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        addReview = new JButton("Add Review");
        refreshReviews = new JButton("Refresh Reviews");

        reviewsArea.setLineWrap(true);
        reviewsArea.setWrapStyleWord(true);
        reviewsArea.setEditable(false);
        reviewsArea.setFont(new Font("Arial", Font.PLAIN, 20));
        final JScrollPane scrollPane = new JScrollPane(reviewsArea);
        final JPanel reviewsPanel = new JPanel();
        reviewsPanel.add(scrollPane);

        final JPanel inputPanel = new JPanel();
        inputPanel.add(newReviewInputField);
        inputPanel.add(addReview);

        addReview.addActionListener(evt -> {
            final double rating = 4.5;
            reviewViewModel.submitReview(newReviewInputField.getText(), rating);
        });

        refreshReviews.addActionListener(evt -> {
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(reviewsPanel);
        this.add(inputPanel);
        this.add(refreshReviews);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("reviews".equals(evt.getPropertyName())) {
            updateReviewsArea();
        }
    }

    private void updateReviewsArea() {
        reviewsArea.setText("");
        for (String review : reviewViewModel.getReviews()) {
            reviewsArea.append(review + "\n");
        }
    }
}
