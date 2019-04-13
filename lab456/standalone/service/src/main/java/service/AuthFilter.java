package service;

import com.sun.jersey.core.util.Base64;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

public class AuthFilter implements ContainerRequestFilter {

    private static final Map<String, String> userPasswordMap = new HashMap<String, String>() {{
        put("ifmo-ws", "ifmo-ws");
    }};

    // Exception thrown if user is unauthorized.
    private final static WebApplicationException unauthorized =
            new WebApplicationException(
                    Response.status(UNAUTHORIZED)
                            .header(HttpHeaders.WWW_AUTHENTICATE, "Basic realm=\"realm\"")
                            .entity("Wrong login or password").build());

    @Override
    public ContainerRequest filter(ContainerRequest containerRequest)
            throws WebApplicationException {

        // Get the authentication passed in HTTP headers parameters
        String auth = containerRequest.getHeaderValue("authorization");
        if (auth == null)
            throw unauthorized;

        auth = auth.replaceFirst("[Bb]asic ", "");
        String usernameAndPassword = Base64.base64Decode(auth);
        String username, password;

        try {
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            username = tokenizer.nextToken();
            password = tokenizer.nextToken();
        } catch (Exception e) {
            Logger.getLogger(AuthFilter.class.getName()).log(Level.SEVERE, null, e);
            throw unauthorized;
        }

        if (!isUserAllowed(username, password))
            throw unauthorized;

        return containerRequest;
    }

    private boolean isUserAllowed(final String username, final String password) {
        String realPassword = userPasswordMap.get(username);
        if (realPassword != null && realPassword.equals(password))
            return true;
        else return false;
    }
}
