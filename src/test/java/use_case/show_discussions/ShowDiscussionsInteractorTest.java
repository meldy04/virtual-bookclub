package use_case.show_discussions;

import data_access.InMemoryBookClubDataAccessObject;
import entity.BookClub;
import entity.Discussion;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ShowDiscussionsInteractorTest {
    @Test
    public void SuccessTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub bookClub = new BookClub("Cooking", "Culinary");
        // adds 2 empty discussions in the book club
        bookClub.addDiscussion("favourite foods", new Discussion("favourite foods", new ArrayList<>()));
        bookClub.addDiscussion("cooking tips", new Discussion("cooking tips", new ArrayList<>()));
        bookClubMap.put("Cooking", bookClub);

        InMemoryBookClubDataAccessObject inMemoryBookClubDataAccessObject = new InMemoryBookClubDataAccessObject(bookClubMap);
        inMemoryBookClubDataAccessObject.setCurrentClub("Cooking"); // setting current book club which is done by another usecase.

        ShowDiscussionsOutputBoundary successPresenter = new ShowDiscussionsOutputBoundary() {
            @Override
            public void prepareSuccessView(ShowDiscussionsOutputData outputData) {
                List<String> expectedOutput = Arrays.asList("favourite foods", "cooking tips");
                assertEquals(expectedOutput, outputData.getTopics());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToAddMessageView(String discussion) {
                fail("Use case failure is unexpected.");
            }
        };
        ShowDiscussionsInputBoundary showDiscussionsInteractor = new ShowDiscussionsInteractor(successPresenter, inMemoryBookClubDataAccessObject);
        showDiscussionsInteractor.execute();
    }


    @Test
    public void FailTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub bookClub = new BookClub("Cooking", "Culinary");
        bookClubMap.put("Cooking", bookClub); // adds book club with no discussions

        InMemoryBookClubDataAccessObject inMemoryBookClubDataAccessObject = new InMemoryBookClubDataAccessObject(bookClubMap);
        inMemoryBookClubDataAccessObject.setCurrentClub("Cooking"); // setting current book club which is done by another usecase.

        ShowDiscussionsOutputBoundary failPresenter = new ShowDiscussionsOutputBoundary() {
            @Override
            public void prepareSuccessView(ShowDiscussionsOutputData outputData) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("There are no discussions here, create a new discussion", errorMessage);
            }

            @Override
            public void switchToAddMessageView(String discussion) {
                fail("Use case failure is unexpected.");
            }
        };

        ShowDiscussionsInputBoundary showDiscussionsInteractor = new ShowDiscussionsInteractor(failPresenter, inMemoryBookClubDataAccessObject);
        showDiscussionsInteractor.execute();
    }
}
