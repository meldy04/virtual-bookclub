package use_case.join_club;

import data_access.InMemoryBookClubDataAccessObject;
import entity.BookClub;
import entity.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class JoinClubInteractorTest {
    @Test
    public void successTest() {
        BookClub cookingClub = new BookClub("Cooking", "Culinary");

        Map<String, BookClub> bookClubMap = new HashMap<>();
        bookClubMap.put("Cooking", cookingClub);
        JoinClubInputData inputData = new JoinClubInputData("Bob", "Cooking");
        JoinClubDataAccessInterface bookClubRepository = new InMemoryBookClubDataAccessObject(bookClubMap);

        JoinClubOutputBoundary successPresenter = new JoinClubOutputBoundary() {
            @Override
            public void prepareSuccessView(JoinClubOutputData outputData) {
                assertTrue(outputData.getUsername().equals("Bob") && outputData.getClubName().equals("Cooking"));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected: " + errorMessage);
            }
        };

        JoinClubInputBoundary joinClubInteractor = new JoinClubInteractor(successPresenter, bookClubRepository);

        joinClubInteractor.execute(inputData);
    }
}