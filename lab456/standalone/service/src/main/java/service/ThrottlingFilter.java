package service;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import service.exceptions.ThrottlingException;

import javax.ws.rs.WebApplicationException;

public class ThrottlingFilter implements ContainerRequestFilter {

    @Override
    public ContainerRequest filter(ContainerRequest containerRequest)
            throws WebApplicationException {
        ThrottlingLogic.addConnection();
        return containerRequest;
    }

}
