package service;

import service.exceptions.DataNotFoundException;
import service.exceptions.DefaultException;
import service.exceptions.FormatException;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;

@RequestScoped
@Path("footballclubs")
@Produces({MediaType.APPLICATION_JSON})
public class FootballClubResource {

    @Resource(lookup = "jdbc/ifmo-ws")
    private DataSource dataSource;

    @GET
    public List<FootballClub> getFootballClubs(
            @QueryParam("id") Integer id,
            @QueryParam("name") String name,
            @QueryParam("country") String country,
            @QueryParam("city") String city,
            @QueryParam("age") Integer age) throws DefaultException {

        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        List<FootballClub> footballClubs = dao.getFootballClubsByFilters(id, name, country, city, age);
        return footballClubs;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFootballClub(
            FootballClub footballClub) throws DefaultException, FormatException {

        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        Integer id = dao.insertFootballClub(footballClub.getName(), footballClub.getCountry(),
                footballClub.getCity(), footballClub.getAge());
        return Response.ok(new Status(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFootballClub(
            FootballClub footballClub) throws DefaultException, DataNotFoundException, FormatException {

        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        Integer status = dao.updateFootballClub(footballClub.getId(),
                footballClub.getName(), footballClub.getCountry(),
                footballClub.getCity(), footballClub.getAge());
        return Response.ok(new Status(status)).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteFootballClub(
            FootballClub footballClub) throws DefaultException, DataNotFoundException, FormatException {

        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        Integer status = dao.deleteFootballClub(footballClub.getId());
        return Response.ok(new Status(status)).build();
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(FootballClubResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}