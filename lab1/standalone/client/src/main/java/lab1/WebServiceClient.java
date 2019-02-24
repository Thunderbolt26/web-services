package lab1;

import lab1.generated.Person;
import lab1.generated.PersonService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class WebServiceClient {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://localhost:8080/PersonService/?wsdl");
        System.out.println("============People Service===========");
        boolean flag = true;
        boolean isStandAlone = true;
        Integer id = null, age = null;
        String name = null, surname = null, patronymic = null;
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Choose one of service realization:");
            while (flag) {
                System.out.println("1. Standalone");
                System.out.println("2. JavaEE");
                System.out.print("Print number: ");
                String type = input.readLine();
                switch (type) {
                    case "1":
                        flag = false;
                        break;
                    case "2":
                        url = new URL("http://localhost:8080/javaee/PersonService?wsdl");
                        flag = false;
                        break;
                    default:
                        System.out.println("Choose only 1 or 2");
                }
            }
            flag = true;
            while (flag) {
                id = null;
                age = null;
                name = null;
                surname = null;
                patronymic = null;
                flag = false;

                System.out.println("Write your filter for fields: id, name, surname, patronymic, age)");
                System.out.println("Format: key=value[,key=value]|[ENTER]");
                System.out.println("Example: name=Даниил,surname=Богомолов");

                String filter = input.readLine();
                if (filter.equals("")) break;
                String filters[] = filter.split(",");
                for (String cond : filters) {
                    if (flag) break;
                    String fieldvalue[] = cond.split("=");
                    if (fieldvalue.length != 2) {
                        System.out.println("Again");
                        flag = true;
                        break;
                    }
                    switch (fieldvalue[0]) {
                        case "id":
                            id = Integer.getInteger(fieldvalue[1]);
                            if (id == null) {
                                flag = true;
                                System.out.println("Again");
                            }
                            break;
                        case "name":
                            name = fieldvalue[1];
                            break;
                        case "surname":
                            surname = fieldvalue[1];
                            break;
                        case "patronymic":
                            patronymic = fieldvalue[1];
                            break;
                        case "age":
                            age = Integer.getInteger(fieldvalue[1]);
                            if (age == null) {
                                flag = true;
                                System.out.println("Again");
                            }
                            break;
                        default:
                            System.out.println("Again");
                            flag = true;
                            break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        PersonService personService = new PersonService(url);

        List<Person> persons = personService.getPersonWebServicePort().getPersonsByFilter(id, name, surname, patronymic, age);
        System.out.println("id: " + id + ", name: " + name + ", surname: " + surname + ", patronymic: " + patronymic + ", age: " + age);
        System.out.println("============Result===========");
        for (Person person : persons) {
            System.out.println("id: " + person.getId() + ", name: " + person.getName() + ", surname: " + person.getSurname() + ", patronymic: " + person.getPatronymic() + ", age: " + person.getAge());
        }
        System.out.println("Total persons: " + persons.size());
    }

}
