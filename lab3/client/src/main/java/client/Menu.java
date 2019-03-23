package client;

import client.generated.*;

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
                    url = new URL("http://localhost:8080/FootballClubService/?wsdl");
                    crudMenu();
                    break;
                case "2":
                    url = new URL("http://localhost:8080/javaee/FootballClubService?wsdl");
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
        FootballClubService footballService = new FootballClubService(url);
        System.out.println("params: name: " + name + ", country: " + country + ", city: " + city + ", age: " + age);

        try {
            id = footballService.getFootballClubWebServicePort().addFootballClub(name, country, city, age);
        } catch (DefaultException e) {
            System.out.println("============Operation failed===========");
            System.out.println(e.getFaultInfo().getMessage());
            return;
        } catch ( FormatException e) {
            System.out.println("============Operation failed===========");
            System.out.println(e.getFaultInfo().getMessage());
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
        FootballClubService footballClubService = new FootballClubService(url);
        Integer status = null;
        try {
            status = footballClubService.getFootballClubWebServicePort().updateFootballClub(id, name, country, city, age);
        } catch (DefaultException e) {
            System.out.println("============Operation failed===========");
            System.out.println(e.getFaultInfo().getMessage());
            return;
        } catch ( FormatException e) {
            System.out.println("============Operation failed===========");
            System.out.println(e.getFaultInfo().getMessage());
            return;
        } catch (DataNotFoundException e) {
            System.out.println("============Operation failed===========");
            System.out.println(e.getFaultInfo().getMessage());
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

        FootballClubService footballClubService = new FootballClubService(url);

        Integer status = null;
        try {
            status = footballClubService.getFootballClubWebServicePort().deleteFootballClub(id);
        } catch (DefaultException e) {
            System.out.println("============Operation failed===========");
            System.out.println(e.getFaultInfo().getMessage());
            return;
        } catch ( FormatException e) {
            System.out.println("============Operation failed===========");
            System.out.println(e.getFaultInfo().getMessage());
            return;
        } catch (DataNotFoundException e) {
            System.out.println("============Operation failed===========");
            System.out.println(e.getFaultInfo().getMessage());
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
        FootballClubService footballClubService = new FootballClubService(url);

        List<FootballClub> footballClubs = null;
        try {
            footballClubs =
                    footballClubService.getFootballClubWebServicePort().getFootballClubsByFilter(id, name, country, city, age);
        } catch (DefaultException e) {
            System.out.println("============Operation failed===========");
            System.out.println(e.getFaultInfo().getMessage());
            return;
        }
        //System.out.println("id: " + id + ", name: " + name + ", country: " + country + ", city: " + city + ", age: " + age);
        System.out.println("============Result===========");
        for (FootballClub footballClub : footballClubs) {
            System.out.println("id: " + footballClub.getId() + ", name: " + footballClub.getName() + ", country: " + footballClub.getCountry() + ", city: " + footballClub.getCity() + ", age: " + footballClub.getAge());
        }
        System.out.println("Total football clubs: " + footballClubs.size());
    }
}
