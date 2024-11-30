package use_case.join_club;

import data_access.InMemoryBookClubDataAccessObject;
import entity.BookClub;
import entity.User;
import org.junit.Test;

import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JoinClubInteractorTest {
    @Test
    public void successTest() {

        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub cookingClub = new BookClub("Cooking", "Culinary");
        bookClubMap.put("Cooking", cookingClub);
        JoinClubInputData inputData = new JoinClubInputData("Bob", "Cooking");
        JoinClubDataAccessInterface bookClubRepository = new InMemoryBookClubDataAccessObject(bookClubMap);
        JoinClubOutputBoundary successPresenter = new JoinClubOutputBoundary() {
            @Override
            public void prepareSuccessView(JoinClubOutputData outputData) {
                assertTrue(outputData.getUsername().equals("Bob") && outputData.getClubName().equals("Cooking"));
            }
            @Override
            public void prepareFailView(String message) {
                fail("Use case failure is unexpected.");
            }
        };
        JoinClubInputBoundary joinClubInteractor = new JoinClubInteractor(successPresenter, bookClubRepository);
        joinClubInteractor.execute(inputData);
    }

    @Test
    public void failTest() {
        Map<String, BookClub> bookClubMap = new HashMap<>();
        BookClub testClub = new BookClub("Cooking", "Culinary");
        testClub.addMember("Bob");
        bookClubMap.put("Cooking", testClub); // creates a book club where Bob is a member

        JoinClubInputData inputData = new JoinClubInputData("Bob", "Cooking");
        JoinClubDataAccessInterface bookClubRepository = new InMemoryBookClubDataAccessObject(bookClubMap);

        JoinClubOutputBoundary failPresenter = new JoinClubOutputBoundary() {
            @Override
            public void prepareSuccessView(JoinClubOutputData outputData) {
                fail("Use case failure is unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User Bob is already a member of the Cooking Book Club", error);

            }
        };
        JoinClubInputBoundary joinClubInteractor = new JoinClubInteractor(failPresenter, bookClubRepository);
        joinClubInteractor.execute(inputData);
    }
}
