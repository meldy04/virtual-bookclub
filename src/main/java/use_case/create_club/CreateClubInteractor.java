package use_case.create_club;

import data_access.InMemoryUserDataAccessObject;
import entity.BookClub;

public class CreateClubInteractor implements CreateClubInputBoundary {

    private final CreateClubOutputBoundary createclubOutputBoundary;
    private final CreateClubDataAccessInterface createclubDataAccessInterface;

    public CreateClubInteractor(CreateClubOutputBoundary createclubOutputBoundary,
                                CreateClubDataAccessInterface createclubDataAccessInterface) {
        this.createclubOutputBoundary = createclubOutputBoundary;
        this.createclubDataAccessInterface = createclubDataAccessInterface;
    }

    // @Override
    public void execute(CreateClubInputData createClubInputData) {
        final String username = createClubInputData.getUsername();
        final String clubName = createClubInputData.getClubName();
        final String clubDescription = createClubInputData.getClubDescription();
        if (createclubDataAccessInterface.clubExists(clubName)) {
            createclubOutputBoundary.prepareFailView(clubName + " already" + " exists.");
        }
        else {
            final BookClub bookclub = new BookClub(clubName, clubDescription);
            final CreateClubOutputData outputData = new CreateClubOutputData(username, clubName, false);
            createclubDataAccessInterface.addClub(bookclub.getName());
            createclubDataAccessInterface.addUser(username, bookclub.getName());
            createclubOutputBoundary.prepareSuccessView(outputData);
        }

    }
}

