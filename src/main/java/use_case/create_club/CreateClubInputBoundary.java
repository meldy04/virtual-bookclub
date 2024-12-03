package use_case.create_club;

public interface CreateClubInputBoundary {

    void execute(CreateClubInputData inputData);

    void switchToLoggedInView();
}
