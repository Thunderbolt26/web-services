package service;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import service.exceptions.ThrottlingException;

public class CloseFilter implements ContainerResponseFilter {

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response)
            throws ThrottlingException {
        ThrottlingLogic.removeConnection();
        return response;
    }

}
