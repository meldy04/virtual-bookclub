package use_case.create_club;

public class CreateClubInputData {
    private final String clubName;
    private final String clubDescription;
    private final String book;
    private String username;

    public CreateClubInputData(String clubName, String clubDescription, String book, String username) {
        this.clubName = clubName;
        this.clubDescription = clubDescription;
        this.book = book;
        this.username = username;
    }

    public String getClubName() {
        return clubName;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    public String getBook() {
        return book;
    }

    public String getUsername() {
        return username;
    }
}
