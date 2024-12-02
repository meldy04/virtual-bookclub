package use_case.create_club;

public class CreateClubOutputData {
    private final String username;
    private final String clubname;
    private final boolean usecaseFailed;

    public CreateClubOutputData(String username, String clubname, boolean usecaseFailed) {
        this.username = username;
        this.clubname = clubname;
        this.usecaseFailed = usecaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getClubname() {
        return clubname;
    }

    public boolean isUsecaseFailed() {
        return usecaseFailed;
    }
}
