package lab1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreSQLDAO {

    public List<Person> getPersonsByFilters(Integer id,
                                            String name,
                                            String surname,
                                            String patronymic,
                                            Integer age) {
        List<Person> persons = new ArrayList<>();

        QueryBuilder queryBuilder = new QueryBuilder("persons");
        if (id != null) queryBuilder.addFilter("id", id);
        if (name != null) queryBuilder.addFilter("name", name);
        if (surname != null) queryBuilder.addFilter("surname", surname);
        if (patronymic != null) queryBuilder.addFilter("patronymic", patronymic);
        if (age != null) queryBuilder.addFilter("age", age);

        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement stmt = connection.createStatement();
            String s = queryBuilder.query();
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.FINE, s);
            ResultSet rs = stmt.executeQuery(s);
            while (rs.next()) {
                int resultId = rs.getInt("id");
                String resultName = rs.getString("name");
                String resultSurname = rs.getString("surname");
                String resultPatronymic = rs.getString("patronymic");
                int resultAge = rs.getInt("age");
                Person person = new Person(resultId, resultName, resultSurname, resultPatronymic, resultAge);
                persons.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }
}
