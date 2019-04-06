package service.exceptions;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.wishmaster.ifmo.ws.jaxws.errors.FootballClubServiceFault")
public class FormatException extends Exception {
    private static final long serialVersionUID = -6239149565765321918L;
    private final FootballClubServiceFault fault;

    public FormatException(String message, FootballClubServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public FormatException(String message, FootballClubServiceFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public FootballClubServiceFault getFaultInfo() {
        return fault;
    }
}
