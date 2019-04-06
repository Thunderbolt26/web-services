package service;

import service.exceptions.DataNotFoundException;
import service.exceptions.DefaultException;
import service.exceptions.FormatException;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/footballclubs")
@Produces({MediaType.APPLICATION_JSON})
public class FootballClubResource {

    @GET
    public List<FootballClub> getFootballClubs(
            @QueryParam("id") Integer id,
            @QueryParam("name") String name,
            @QueryParam("country") String country,
            @QueryParam("city") String city,
            @QueryParam("age") Integer age) throws DefaultException {

        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<FootballClub> footballClubs = dao.getFootballClubsByFilters(id, name, country, city, age);
        return footballClubs;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addFootballClub(
            FootballClub footballClub) throws DefaultException, FormatException {

        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer id = dao.insertFootballClub(footballClub.getName(), footballClub.getCountry(),
                footballClub.getCity(), footballClub.getAge());
        return Response.ok(new Status(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateFootballClub(
            FootballClub footballClub) throws DefaultException, DataNotFoundException, FormatException {

        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer status = dao.updateFootballClub(footballClub.getId(),
                footballClub.getName(), footballClub.getCountry(),
                footballClub.getCity(), footballClub.getAge());
        return Response.ok(new Status(status)).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteFootballClub(
            FootballClub footballClub) throws DefaultException, DataNotFoundException, FormatException {

        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer status = dao.deleteFootballClub(footballClub.getId());
        return Response.ok(new Status(status)).build();
    }
}