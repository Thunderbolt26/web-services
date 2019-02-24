package lab1;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(serviceName = "PersonService")
public class PersonWebService {

    @Resource(lookup = "jdbc/ifmo-ws")
    private DataSource dataSource;

    @WebMethod(operationName = "getPersonsByFilter")
    public List<Person> getPersons(
            @WebParam(name = "personId") Integer id,
            @WebParam(name = "personName") String name,
            @WebParam(name = "personSurname") String surname,
            @WebParam(name = "personPatronymic") String patronymic,
            @WebParam(name = "personAge") Integer age) {

        PostgreSQLDAO dao = new PostgreSQLDAO(getConnection());
        List<Person> persons = dao.getPersonsByFilters(id, name, surname, patronymic, age);
        return persons;
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            result = dataSource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PersonWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
