package client;

import client.generated.Person;
import client.generated.PersonService;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Menu {
    private URL url;
    private BufferedReader input;

    public Menu(BufferedReader input) {
        this.input = input;
    }

    private void readStatus(int id) {
        System.out.println("Status code: " + id);
        switch (id) {
            case 0:
                System.out.println("Status: Operation was succeed");
                break;
            case -1:
                System.out.println("Status: Error");
                break;
            case -2:
                System.out.println("Status: No data with specified id");
                break;
            case -3:
                System.out.println("Status: Args can not be null");
                break;
            default:
                System.out.println("Status: Unknown");
                break;
        }
    }

    private void again() {

        System.out.println("Choose again");
    }

    public void execute() throws IOException {
        boolean flag = true;
        System.out.println("Choose one of service realization:");
        while (flag) {
            System.out.println("1. Standalone");
            System.out.println("2. JavaEE");
            System.out.println("3. Exit");
            System.out.print("Print number: ");
            String type = input.readLine();
            switch (type) {
                case "1":
                    url = new URL("http://localhost:8080/PersonService/?wsdl");
                    crudMenu();
                    break;
                case "2":
                    url = new URL("http://localhost:8080/javaee/PersonService?wsdl");
                    crudMenu();
                    break;
                case "3":
                    flag = false;
                    break;
                default:
                    again();
            }
        }
    }

    private void crudMenu() throws IOException {
        boolean flag = true;
        System.out.println("Choose one of service");
        while (flag) {
            System.out.println("1. Add person");
            System.out.println("2. Update person");
            System.out.println("3. Delete person");
            System.out.println("4. Find persons");
            System.out.println("5. Exit");
            System.out.print("Print number: ");
            String type = input.readLine();
            switch (type) {
                case "1":
                    addPersonMenu();
                    break;
                case "2":
                    updatePersonMenu();
                    break;
                case "3":
                    deletePersonMenu();
                    break;
                case "4":
                    findPersonMenu();
                    break;
                case "5":
                    flag = false;
                    break;
                default:
                    again();
            }
        }
    }

    private void addPersonMenu() throws IOException {
        boolean flag = true;

        Integer id = null, age = null;
        String name = null, surname = null, patronymic = null;
        System.out.println("===Add person===");
        while (flag) {
            age = null;
            name = null;
            surname = null;
            patronymic = null;
            flag = false;

            System.out.println("Write values for fields: name, surname, patronymic, age)");
            System.out.println("Format: key=value[,key=value]|[ENTER]");
            System.out.println("Example: name=Иван,surname=Иванов,patronymic=Иванович,age=10");

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
                        try {
                            age = Integer.parseInt(fieldvalue[1]);
                        } catch (NumberFormatException e) {
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
        PersonService personService = new PersonService(url);
        System.out.println("params: name: " + name + ", surname: " + surname + ", patronymic: " + patronymic + ", age: " + age);

        id = personService.getPersonWebServicePort().addPersons(name, surname, patronymic, age);
        System.out.println("============Result===========");
        if (id >= 0)
            System.out.println("New person's id: " + id);
        else if (id == -2)
            System.out.println("Args can not be null");
        else
            System.out.println("Operation failed");
    }

    private Integer readId() throws IOException {
        Integer id = null;
        boolean flag = true;
        while (flag) {
            System.out.println("===Enter id of person===");
            String s = input.readLine();
            flag = false;
            try {
                id = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                flag = true;
                System.out.println("Again");
            }
        }
        return id;
    }

    private void updatePersonMenu() throws IOException {
        boolean flag = true;

        Integer id = null, age = null;
        String name = null, surname = null, patronymic = null;
        System.out.println("===Update person===");
        id = readId();
        while (flag) {
            age = null;
            name = null;
            surname = null;
            patronymic = null;
            flag = false;

            System.out.println("Write new values for fields: name, surname, patronymic, age)");
            System.out.println("Format: key=value[,key=value]|[ENTER]");
            System.out.println("Example: name=Иван,surname=Иванов");

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
                        try {
                            age = Integer.parseInt(fieldvalue[1]);
                        } catch (NumberFormatException e) {
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
        PersonService personService = new PersonService(url);

        Integer status = personService.getPersonWebServicePort().updatePerson(id, name, surname, patronymic, age);
        System.out.println("============Result===========");
        if (status != null)
            readStatus(status);
        else
            System.out.println("Operation failed");
    }

    private void deletePersonMenu() throws IOException {
        Integer id = null;
        System.out.println("===Delete person===");
        id = readId();

        PersonService personService = new PersonService(url);
        Integer status = personService.getPersonWebServicePort().deletePerson(id);
        System.out.println("============Result===========");
        if (status != null)
            readStatus(status);
        else
            System.out.println("Operation failed");
    }


    private void findPersonMenu() throws IOException {
        boolean flag = true;

        Integer id = null, age = null;
        String name = null, surname = null, patronymic = null;
        System.out.println("===Find persons===");
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
                        try {
                            id = Integer.parseInt(fieldvalue[1]);
                        } catch (NumberFormatException e) {
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
                        try {
                            age = Integer.parseInt(fieldvalue[1]);
                        } catch (NumberFormatException e) {
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
        PersonService personService = new PersonService(url);

        List<Person> persons = personService.getPersonWebServicePort().getPersonsByFilter(id, name, surname, patronymic, age);

        //System.out.println("id: " + id + ", name: " + name + ", surname: " + surname + ", patronymic: " + patronymic + ", age: " + age);
        System.out.println("============Result===========");
        for (Person person : persons) {
            System.out.println("id: " + person.getId() + ", name: " + person.getName() + ", surname: " + person.getSurname() + ", patronymic: " + person.getPatronymic() + ", age: " + person.getAge());
        }
        System.out.println("Total persons: " + persons.size());
    }
}
