package service;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.Grizzly;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.nio.transport.TCPNIOTransport;
import org.glassfish.grizzly.nio.transport.TCPNIOTransportBuilder;
import org.glassfish.grizzly.threadpool.ThreadPoolConfig;

public class App {

    private static final URI BASE_URI = URI.create("http://localhost:8080/rest/");

    public static void main(String[] args) {
        HttpServer server = null;
        try {
            ResourceConfig resourceConfig = new PackagesResourceConfig(FootballClubResource.class.getPackage().getName());
            //ResourceConfig resourceConfig = new ClassNamesResourceConfig(FootballClubResource.class);
            resourceConfig.getProperties().put("com.sun.jersey.spi.container.ContainerRequestFilters",
                    "service.ThrottlingFilter,service.AuthFilter");
            resourceConfig.getProperties().put("com.sun.jersey.spi.container.ContainerResponseFilters",
                    "service.CloseFilter");
            server = GrizzlyServerFactory.createHttpServer(BASE_URI, resourceConfig);
            server.start();
            System.in.read();
            stopServer(server);
        } catch (IOException e) {
            e.printStackTrace();
            stopServer(server);
        }
    }

    private static void stopServer(HttpServer server) {
        if (server != null) server.stop();
    }

}
