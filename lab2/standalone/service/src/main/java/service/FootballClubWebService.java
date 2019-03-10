package service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "FootballClubService")
public class FootballClubWebService {

    @WebMethod(operationName = "getFootballClubsByFilter")
    public List<FootballClub> getFootballClubs(
            @WebParam(name = "footballClubId") Integer id,
            @WebParam(name = "footballClubName") String name,
            @WebParam(name = "footballClubCountry") String country,
            @WebParam(name = "footballClubCity") String city,
            @WebParam(name = "footballClubAge") Integer age) {

        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<FootballClub> footballClubs = dao.getFootballClubsByFilters(id, name, country, city, age);
        return footballClubs;
    }

    @WebMethod(operationName = "addFootballClub")
    public Integer addFootballClub(
            @WebParam(name = "footballClubName") String name,
            @WebParam(name = "footballClubCountry") String country,
            @WebParam(name = "footballClubCity") String city,
            @WebParam(name = "footballClubAge") Integer age) {

        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer id = dao.insertFootballClub(name, country, city, age);
        return id;
    }

    @WebMethod(operationName = "updateFootballClub")
    public Integer updateFootballClub(
            @WebParam(name = "footballClubId") Integer id,
            @WebParam(name = "footballClubName") String name,
            @WebParam(name = "footballClubCountry") String country,
            @WebParam(name = "footballClubCity") String city,
            @WebParam(name = "footballClubAge") Integer age) {

        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer status = dao.updateFootballClub(id, name, country, city, age);
        return status;
    }

    @WebMethod(operationName = "deleteFootballClub")
    public Integer deleteFootballClub(
            @WebParam(name = "footballClubId") Integer id) {

        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer status = dao.deleteFootballClub(id);
        return status;
    }
}