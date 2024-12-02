import entity.User;
import interface_adapter.recommendations.RecommendationsController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HomeScreen extends JFrame {

    public HomeScreen() {
        setTitle("Home Screen");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);

        final JButton recommendButton = new JButton("Get Recommendations");
        recommendButton.addActionListener(this::showRecommendationsView);
        add(recommendButton);
    }

    private void showRecommendationsView(ActionEvent e) {
        // Create user instance for demo
        final User user = new User("Alice", "password123");

        final RecommendationsController controller = new RecommendationsController(user);
        controller.showRecommendations(this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            final HomeScreen homeScreen = new HomeScreen();
            homeScreen.setVisible(true);
        });
    }
}