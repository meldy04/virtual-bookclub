package interface_adapter.create_club;

public class CreateClubState {
    private String username = "";
    private String clubName = "";
    private boolean created;
    private String clubDescription = "";
    private String errorMessage = "";

    public CreateClubState() {

    }

    public CreateClubState(interface_adapter.create_club.CreateClubState copy) {
        this.username = copy.username;
        this.clubName = copy.clubName;
        this.created = copy.created;
        this.errorMessage = copy.errorMessage;
        this.clubDescription = copy.clubDescription;
    }

    public String getClubDescription() {
        return clubDescription;
    }

    public void setClubDescription(String clubDescription1) {
        this.clubDescription = clubDescription1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username1) {
        this.username = username1;
    }

    public String getBookclub() {
        return clubName;
    }

    public void setBookclub(String clubName1) {
        this.clubName = clubName1;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created1) {
        this.created = created1;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage1) {
        this.errorMessage = errorMessage1;
    }

}
