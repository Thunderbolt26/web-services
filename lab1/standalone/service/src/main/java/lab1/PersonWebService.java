package lab1;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "PersonService")
public class PersonWebService {

    @WebMethod(operationName = "getPersonsByFilter")
    public List<Person> getPersons(
            @WebParam(name = "personId") Integer id,
            @WebParam(name = "personName") String name,
            @WebParam(name = "personSurname") String surname,
            @WebParam(name = "personPatronymic") String patronymic,
            @WebParam(name = "personAge") Integer age) {

        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.getPersonsByFilters(id, name, surname, patronymic, age);
        return persons;
    }
}