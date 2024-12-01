package use_case.recommendations;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import entity.Book;
import interface_adapter.recommendations.RecommendationsViewModel;
import view.RecommendationsView;

public class RecommendationsViewTest {

    private FrameFixture window;
    private RecommendationsViewModel viewModel;
    private JFrame testFrame;

    @Before
    public void setUp() {
        GuiActionRunner.execute(() -> {
            List<Book> books = Arrays.asList(
                    new Book("Book 1", "Author 1", "Genre 1", 4.0),
                    new Book("Book 2", "Author 2", "Genre 2", 4.5)
            );

            viewModel = new RecommendationsViewModel(books);
            RecommendationsView recommendationsView = new RecommendationsView(viewModel,
                    evt -> {}, // Dummy action listener for "Want to Read"
                    evt -> {}  // Dummy action listener for "Ignore"
            );

            testFrame = new JFrame();
            testFrame.setContentPane(recommendationsView);
            testFrame.pack();
            return testFrame;
        });

        window = new FrameFixture(testFrame);
        window.show();
    }

    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void shouldDisplayRecommendations() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        window.target().addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                latch.countDown();
            }
        });
        latch.await();
    }
}
