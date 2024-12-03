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

import interface_adapter.recommendations.RecommendationViewModel;
/**
 * Recommendations View Structure.
 */
public class RecommendationView extends JPanel implements PropertyChangeListener {
    private final RecommendationViewModel recommendationViewModel;
    private final JTextArea recommendationsArea = new JTextArea(25, 25);
    private final JButton refresh;

    /**
     * A panel that displays book recommendations.
     * @param recommendationViewModel .
     */
    public RecommendationView(RecommendationViewModel recommendationViewModel) {
        this.recommendationViewModel = recommendationViewModel;
        recommendationViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Recommendations");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        refresh = new JButton("Refresh Recommendations");

        recommendationsArea.setLineWrap(true);
        recommendationsArea.setWrapStyleWord(true);
        recommendationsArea.setEditable(false);
        recommendationsArea.setFont(new Font("Arial", Font.PLAIN, 20));
        final JScrollPane scrollPane = new JScrollPane(recommendationsArea);
        final JPanel recommendationsPanel = new JPanel();
        recommendationsPanel.add(scrollPane);

        refresh.addActionListener(evt -> {
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(recommendationsPanel);
        this.add(refresh);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("recommendations".equals(evt.getPropertyName())) {
            updateRecommendationsArea();
        }
    }

    private void updateRecommendationsArea() {
        recommendationsArea.setText("");
        for (String recommendation : recommendationViewModel.getRecommendations()) {
            recommendationsArea.append(recommendation + "\n");
        }
    }
}
