package service;

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

    @WebMethod(operationName = "addPersons")
    public Integer addPersons(
            @WebParam(name = "personName") String name,
            @WebParam(name = "personSurname") String surname,
            @WebParam(name = "personPatronymic") String patronymic,
            @WebParam(name = "personAge") Integer age) {

        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer id = dao.insertPerson(name, surname, patronymic, age);
        return id;
    }

    @WebMethod(operationName = "updatePerson")
    public Integer updatePerson(
            @WebParam(name = "personId") Integer id,
            @WebParam(name = "personName") String name,
            @WebParam(name = "personSurname") String surname,
            @WebParam(name = "personPatronymic") String patronymic,
            @WebParam(name = "personAge") Integer age) {

        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer status = dao.updatePerson(id, name, surname, patronymic, age);
        return status;
    }

    @WebMethod(operationName = "deletePerson")
    public Integer deletePerson(
            @WebParam(name = "personId") Integer id) {

        PostgreSQLDAO dao = new PostgreSQLDAO();
        Integer status= dao.deletePerson(id);
        return status;
    }
}