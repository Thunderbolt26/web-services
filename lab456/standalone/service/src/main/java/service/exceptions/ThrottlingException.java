package service.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;

public class ThrottlingException extends WebApplicationException {
    private static final long serialVersionUID = -7046549738039047678L;
    public static ThrottlingException DEFAULT_INSTANCE = new ThrottlingException(
            Response.status(SERVICE_UNAVAILABLE)
                    .entity("Connection pool is full, try again later").build());

    public ThrottlingException(Response r) {
        super(r);
    }
}
