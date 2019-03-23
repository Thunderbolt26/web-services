package service.exceptions;

public class FootballClubServiceFault {
    private static final String DEFAULT_MESSAGE = "Unknown error";
    protected String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static FootballClubServiceFault defaultInstance() {
        FootballClubServiceFault fault = new FootballClubServiceFault();
        fault.message = DEFAULT_MESSAGE;
        return fault;
    }

    public static FootballClubServiceFault getInstance(String message) {
        FootballClubServiceFault fault = new FootballClubServiceFault();
        fault.message = message;
        return fault;
    }

}
