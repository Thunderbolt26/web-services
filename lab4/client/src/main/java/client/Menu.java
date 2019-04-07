package client;


import client.exceptions.ServiceException;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Menu {
    private String url;
    private BufferedReader input;
    private Client client;

    public Menu(BufferedReader input) {
        this.client = Client.create();
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
                    url = "http://localhost:8080/rest/footballclubs";
                    crudMenu();
                    break;
                case "2":
                    //url = new URL("http://localhost:8080/rest/footballclubs");
                    url = "http://localhost:8080/javaee_war_exploded/rest/footballclubs";
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
            System.out.println("1. Add football club");
            System.out.println("2. Update football club");
            System.out.println("3. Delete football club");
            System.out.println("4. Find football clubs");
            System.out.println("5. Exit");
            System.out.print("Print number: ");
            String type = input.readLine();
            switch (type) {
                case "1":
                    addFootballClubMenu();
                    break;
                case "2":
                    updateFootballClubMenu();
                    break;
                case "3":
                    deleteFootballClubMenu();
                    break;
                case "4":
                    findFootballClubMenu();
                    break;
                case "5":
                    flag = false;
                    break;
                default:
                    again();
            }
        }
    }

    private void addFootballClubMenu() throws IOException {
        boolean flag = true;

        Integer id = null, age = null;
        String name = null, country = null, city = null;
        System.out.println("===Add football club===");
        while (flag) {
            age = null;
            name = null;
            country = null;
            city = null;
            flag = false;

            System.out.println("Write values for fields: name, country, city, age)");
            System.out.println("Format: key=value[,key=value]|[ENTER]");
            System.out.println("Example: name=FC Zenit,country=Russia,city=SPB,age=60");

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
                    case "country":
                        country = fieldvalue[1];
                        break;
                    case "city":
                        city = fieldvalue[1];
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
        System.out.println("params: name: " + name + ", country: " + country + ", city: " + city + ", age: " + age);

        try {
            id = addFootballClub(name, country, city, age).getCode();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("============Result===========");
        System.out.println("New football club's id: " + id);
    }

    private Integer readId() throws IOException {
        Integer id = null;
        boolean flag = true;
        while (flag) {
            System.out.println("===Enter id of football club===");
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

    private void updateFootballClubMenu() throws IOException {
        boolean flag = true;

        Integer id = null, age = null;
        String name = null, country = null, city = null;
        System.out.println("===Update football club===");
        id = readId();
        while (flag) {
            age = null;
            name = null;
            country = null;
            city = null;
            flag = false;

            System.out.println("Write new values for fields: name, country, city, age)");
            System.out.println("Format: key=value[,key=value]|[ENTER]");
            System.out.println("Example: name=FC Zenit,country=Russia");

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
                    case "country":
                        country = fieldvalue[1];
                        break;
                    case "city":
                        city = fieldvalue[1];
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

        Integer status = null;
        try {
            status = updateFootballClub(id, name, country, city, age).getCode();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("============Result===========");
        if (status != null)
            readStatus(status);
        else
            System.out.println("Operation failed");
    }

    private void deleteFootballClubMenu() throws IOException {
        Integer id = null;
        System.out.println("===Delete football club===");
        id = readId();

        Integer status = null;
        try {
            status = deleteFootballClub(id).getCode();
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("============Result===========");
        if (status != null)
            readStatus(status);
        else
            System.out.println("Operation failed");
    }

    private void findFootballClubMenu() throws IOException {
        boolean flag = true;

        Integer id = null, age = null;
        String name = null, country = null, city = null;
        System.out.println("===Find football clubs===");
        while (flag) {
            id = null;
            age = null;
            name = null;
            country = null;
            city = null;
            flag = false;

            System.out.println("Write your filter for fields: id, name, country, city, age)");
            System.out.println("Format: key=value[,key=value]|[ENTER]");
            System.out.println("Example: name=FC Zenit,country=Russia");

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
                    case "country":
                        country = fieldvalue[1];
                        break;
                    case "city":
                        city = fieldvalue[1];
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
        System.out.println("============Result===========");
        try {
            printList(getFootballClubs(id, name, country, city, age));
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private Status addFootballClub(String name, String country, String city, Integer age) throws ServiceException {
        WebResource webResource = client.resource(url);

        JsonObject footballClub = new JsonObject();
        if (name != null) footballClub.addProperty("name", name);
        if (country != null) footballClub.addProperty("country", country);
        if (city != null) footballClub.addProperty("city", city);
        if (age != null) footballClub.addProperty("age", age.toString());
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, footballClub.toString());
        //System.out.println(response.toString());
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            GenericType<String> type = new GenericType<String>() {
            };
            String msg = response.getEntity(type);
            if (msg != null) throw new ServiceException(msg);
            else throw new ServiceException("Request failed");
        }
        GenericType<Status> type = new GenericType<Status>() {
        };
        return response.getEntity(type);
    }

    private Status updateFootballClub(Integer id, String name, String country, String city, Integer age) throws ServiceException {
        WebResource webResource = client.resource(url);

        JsonObject footballClub = new JsonObject();
        if (id != null) footballClub.addProperty("id", id.toString());
        if (name != null) footballClub.addProperty("name", name);
        if (country != null) footballClub.addProperty("country", country);
        if (city != null) footballClub.addProperty("city", city);
        if (age != null) footballClub.addProperty("age", age.toString());
        //System.out.println(footballClub.toString());
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, footballClub.toString());
        //System.out.println(response.toString());
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            GenericType<String> type = new GenericType<String>() {
            };
            String msg = response.getEntity(type);
            if (msg != null) throw new ServiceException(msg);
            else throw new ServiceException("Request failed");
        }
        GenericType<Status> type = new GenericType<Status>() {
        };
        return response.getEntity(type);
    }

    private Status deleteFootballClub(Integer id) throws ServiceException {
        WebResource webResource = client.resource(url);
        JsonObject footballClub = new JsonObject();
        if (id != null) footballClub.addProperty("id", id.toString());
        //System.out.println(footballClub.toString());
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class, footballClub.toString());
        //System.out.println(response.toString());
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            GenericType<String> type = new GenericType<String>() {
            };
            String msg = response.getEntity(type);
            if (msg != null) throw new ServiceException(msg);
            else throw new ServiceException("Request failed");

        }
        GenericType<Status> type = new GenericType<Status>() {
        };
        return response.getEntity(type);
    }

    private List<FootballClub> getFootballClubs(Integer id, String name, String country, String city, Integer age) throws ServiceException {
        WebResource webResource = client.resource(url);

        if (id != null) webResource = webResource.queryParam("id", id.toString());
        if (name != null) webResource = webResource.queryParam("name", name);
        if (country != null) webResource = webResource.queryParam("country", country);
        if (city != null) webResource = webResource.queryParam("city", city);
        if (age != null) webResource = webResource.queryParam("age", age.toString());

        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            GenericType<String> type = new GenericType<String>() {
            };
            String msg = response.getEntity(type);
            if (msg != null) throw new ServiceException(msg);
            else throw new ServiceException("Request failed");
        }
        GenericType<List<FootballClub>> type = new GenericType<List<FootballClub>>() {
        };
        return response.getEntity(type);
    }

    private static void printList(List<FootballClub> clubs) {
        for (FootballClub club : clubs) {
            System.out.println(club);
        }
        System.out.println("Total football clubs: " + clubs.size());
    }


}
