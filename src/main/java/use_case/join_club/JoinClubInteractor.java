package use_case.join_club;

import java.util.Map;

import entity.BookClub;

/**
 * The JoinClubInteractor is responsible for handling the logic related to a user joining a book club.
 * It checks membership status, adds the user to the club, and interacts with the data access interface
 * and the output boundary to prepare the appropriate response.
 */
public final class JoinClubInteractor implements JoinClubInputBoundary {

    private final JoinClubOutputBoundary joinClubOutputBoundary;
    private final JoinClubDataAccessInterface clubDataAccessInterface;

    /**
     * Constructs a new JoinClubInteractor with the required dependencies.
     *
     * @param joinClubOutputBoundary      the output boundary to prepare the success or fail view
     * @param clubDataAccessInterface     the data access interface for interacting with book club data
     */
    public JoinClubInteractor(final JoinClubOutputBoundary joinClubOutputBoundary,
                              final JoinClubDataAccessInterface clubDataAccessInterface) {
        this.joinClubOutputBoundary = joinClubOutputBoundary;
        this.clubDataAccessInterface = clubDataAccessInterface;
    }

    /**
     * Executes the process of joining a book club by checking if the user is already a member and
     * then adding the user if they are not a member.
     *
     * @param joinClubInputData the input data containing the username and club name
     */
    @Override
    public void execute(final JoinClubInputData joinClubInputData) {
        final String username = joinClubInputData.getUsername();
        final String clubName = joinClubInputData.getClubName();

        // Retrieve the book club map and find the requested book club
        final Map<String, BookClub> bookClubMap = clubDataAccessInterface.getBookClubMap();
        final BookClub bookClub = bookClubMap.get(clubName);

        // Check if the user is already a member
        if (clubDataAccessInterface.isMember(username, clubName)) {
            joinClubOutputBoundary.prepareFailView("You can't join this club as you are already a member.");
        }
        else {
            // Add the user to the book club
            clubDataAccessInterface.addUser(username, clubName);

            // Prepare success view with updated data
            final JoinClubOutputData outputData = new JoinClubOutputData(username, bookClub, false);
            joinClubOutputBoundary.prepareSuccessView(outputData);
        }
    }
}
