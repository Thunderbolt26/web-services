package service.exceptions;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.wishmaster.ifmo.ws.jaxws.errors.FootballClubServiceFault")
public class DataNotFoundException extends Exception {

    private static final long serialVersionUID = -7046549738039047683L;
    private final FootballClubServiceFault fault;

    public DataNotFoundException(String message, FootballClubServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public DataNotFoundException(String message, FootballClubServiceFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public FootballClubServiceFault getFaultInfo() {
        return fault;
    }
}