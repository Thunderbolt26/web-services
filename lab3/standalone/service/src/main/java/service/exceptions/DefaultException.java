package service.exceptions;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "com.wishmaster.ifmo.ws.jaxws.errors.FootballClubServiceFault")
public class DefaultException extends Exception {
    private static final long serialVersionUID = 5036119094557642747L;
    private final FootballClubServiceFault fault;

    public DefaultException() {
        this.fault = FootballClubServiceFault.defaultInstance();
    }

    public DefaultException(String message, FootballClubServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public DefaultException(String message, FootballClubServiceFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public FootballClubServiceFault getFaultInfo() {
        return fault;
    }
}
