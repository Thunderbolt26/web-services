package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {



    public final static String INSERT_ROW = "INSERT INTO football_clubs(name, country, city, age) values (?, ?, ?, ?) RETURNING id;";
    public final static String DELETE_ROW = "DELETE FROM football_clubs WHERE id = ? RETURNING id;";

    public final static Integer CODE_OK = 0;
    public final static Integer CODE_ERROR = -1;
    public final static Integer CODE_NO_DATA = -2;
    public final static Integer CODE_NULL_ARG = -3;


    public Integer insertFootballClub(String name,
                                String country,
                                String city,
                                Integer age) {
        Integer id;
        if(name == null || country == null || city == null || age == null) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, "Not valid args");
            return CODE_NULL_ARG;
        }
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROW);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, country);
            preparedStatement.setString(3, city);
            preparedStatement.setInt(4, age);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
                return id;
            }
            else return CODE_ERROR;
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return CODE_ERROR;
        }
    }

    public Integer deleteFootballClub(Integer id) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROW);
            preparedStatement.setInt(1, id);
            //rc = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                Integer rc = rs.getInt(1);
                return CODE_OK;
            }
            else return CODE_NO_DATA;
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return CODE_ERROR;
        }
    }

    public Integer updateFootballClub(Integer id,
                                String name,
                                String country,
                                String city,
                                Integer age) {

        UpdateBuilder queryBuilder = new UpdateBuilder("football_clubs","id");
        System.out.println("id:" + id);
        if (id != null) queryBuilder.addFilter("id", id);
        if (name != null) queryBuilder.addSetter("name", name);
        if (country != null) queryBuilder.addSetter("country", country);
        if (city != null) queryBuilder.addSetter("city", city);
        if (age != null) queryBuilder.addSetter("age", age);

        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement stmt = connection.createStatement();
            String s = queryBuilder.query();
            if(s == null) return -1;
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, s);
            ResultSet rs = stmt.executeQuery(s);
            if(rs.next()) {
                Integer rc = rs.getInt(1);
                return CODE_OK;
            }
            else return CODE_NO_DATA;

            /*if(rs.next())
                rc = rs.getInt(1);
            else rc = -3;*/
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return CODE_ERROR;
        }
    }

    public List<FootballClub> getFootballClubsByFilters(Integer id,
                                                  String name,
                                                  String country,
                                                  String city,
                                                  Integer age) {
        List<FootballClub> footballClubs = new ArrayList<>();

        QueryBuilder queryBuilder = new QueryBuilder("football_clubs");
        if (id != null) queryBuilder.addFilter("id", id);
        if (name != null) queryBuilder.addFilter("name", name);
        if (country != null) queryBuilder.addFilter("country", country);
        if (city != null) queryBuilder.addFilter("city", city);
        if (age != null) queryBuilder.addFilter("age", age);

        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement stmt = connection.createStatement();
            String s = queryBuilder.query();
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.FINE, s);
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                int resultId = rs.getInt("id");
                String resultName = rs.getString("name");
                String resultSurname = rs.getString("country");
                String resultPatronymic = rs.getString("city");
                int resultAge = rs.getInt("age");
                FootballClub footballClub = new FootballClub(resultId, resultName, resultSurname, resultPatronymic, resultAge);
                footballClubs.add(footballClub);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return footballClubs;
    }
}
