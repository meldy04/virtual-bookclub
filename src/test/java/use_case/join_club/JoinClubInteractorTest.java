package use_case.join_club;

import data_access.InMemoryBookClubDataAccessObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class JoinClubInteractorTest {
    @Test
    public void successTest() {
        Map<String, List<String>> bookClubMap = new HashMap<>();
        bookClubMap.put("Cooking", new ArrayList<>()); // creates empty book club

        JoinClubInputData inputData = new JoinClubInputData("Bob", "Cooking");
        JoinClubDataAccessInterface bookClubRepository = new InMemoryBookClubDataAccessObject(bookClubMap);

        JoinClubOutputBoundary successPresenter = new JoinClubOutputBoundary() {
            @Override
            public void prepareSuccessView(JoinClubOutputData outputData) {
                assertTrue(outputData.getUsername().equals("Bob") && outputData.getClubName().equals("Cooking"));

            }

            @Override
            public void prepareFailView() {
                fail("Use case failure is unexpected.");
            }
        };
        JoinClubInputBoundary joinClubInteractor = new JoinClubInteractor(successPresenter, bookClubRepository);
        joinClubInteractor.execute(inputData);
    }

}
