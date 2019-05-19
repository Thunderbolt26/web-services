package client;

import client.generated.*;
import client.juddi.Browser;
import client.juddi.BrowserQuery;
import client.juddi.Publisher;
import client.juddi.ValidationException;

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
        System.out.println("Choose one of the options:");
        while (flag) {
            System.out.println("1. Registration");
            System.out.println("2. Search");
            System.out.println("3. Exit");
            System.out.print("Print number: ");
            String type = input.readLine();
            switch (type) {
                case "1":
                    registrationMenu();
                    break;
                case "2":
                    searchMenu();
                    break;
                case "3":
                    flag = false;
                    break;
                default:
                    again();
            }
        }
    }

    public void registrationMenu() throws IOException {
        boolean flag = true;
        Publisher.PublishBuilder builder = Publisher.builder();
        Publisher publisher = null;
        String userId;
        String cred;
        String businessName;
        String serviceName;
        String wsdl;

        System.out.println("Enter all vars:");
        while (flag) {
            System.out.println("1. User Id");
            System.out.println("2. Password");
            System.out.println("3. Business Name");
            System.out.println("4. Service Name");
            System.out.println("5. wsdl");
            System.out.println("6. Submit");
            System.out.println("7. Exit");
            System.out.print("Print number: ");
            String type = input.readLine();
            switch (type) {
                case "1":
                    System.out.println("User Id: ");
                    userId = input.readLine();
                    builder.setUserId(userId);
                    break;
                case "2":
                    System.out.println("Password: ");
                    cred = input.readLine();
                    builder.setCred(cred);
                    break;
                case "3":
                    System.out.println("Business Name: ");
                    businessName = input.readLine();
                    builder.setBusinessName(businessName);
                    break;
                case "4":
                    System.out.println("Service Name: ");
                    serviceName = input.readLine();
                    builder.setServiceName(serviceName);
                    break;
                case "5":
                    System.out.println("wsdl: ");
                    wsdl = input.readLine();
                    builder.setWsdl(wsdl);
                    break;
                case "6":
                    System.out.println(builder);
                    System.out.println("Submitting... ");

                    try {
                        publisher = builder.build();
                    } catch (ValidationException e) {
                        System.out.println(e.getMessage());
                        break;
                    }

                    int rc = publisher.publish();
                    if(rc == publisher.CODE_ERR) {
                        System.out.println("Submit is not successful");
                    } else System.out.println("Success!");
                    break;
                case "7":
                    flag = false;
                    break;
                default:
                    again();
            }
        }
    }

    public void searchMenu() throws IOException {
        boolean flag = true;
        Browser br = new Browser();
        BrowserQuery.Subject subject;
        String searchString;
        BrowserQuery query = null;
        System.out.println("Choose subject to searching");
        while (flag) {
            System.out.println("1. Business");
            System.out.println("2. Service");
            System.out.println("3. WSDL");
            System.out.println("4. Exit");
            System.out.print("Print number: ");
            String type = input.readLine();
            switch (type) {
                case "1":
                    System.out.println("Search business. Enter string for approximateMatch by Name:");
                    subject = BrowserQuery.Subject.BUSINESS;
                    searchString = input.readLine();
                    query = new BrowserQuery(subject, searchString);
                    br.browse(query);
                    break;
                case "2":
                    System.out.println("Search service. Enter string for approximateMatch by Name:");
                    subject = BrowserQuery.Subject.SERVICE;
                    searchString = input.readLine();
                    query = new BrowserQuery(subject, searchString);
                    br.browse(query);
                    break;
                case "3":
                    System.out.println("Search and call WSDL by ServiceKey. Enter key of service:");
                    subject = BrowserQuery.Subject.WSDL;
                    searchString = input.readLine();
                    query = new BrowserQuery(subject, searchString);
                    br.browse(query);
                    //url = new URL("http://localhost:8090/FootballClubService/?wsdl");
                    if(br.wsdl!=null){
                        System.out.println("Founded WSDL: " + br.wsdl);
                        wsdlMenu(br.wsdl);
                    } else {
                        System.out.println("WSDL is not founded");
                    }
                    break;
                case "4":
                    flag = false;
                    break;
                default:
                    again();
            }
        }
    }

    public void wsdlMenu(String wsdl) throws IOException {
        boolean flag = true;
        url = new URL(wsdl);
        while (flag) {
            System.out.println("Use wsdl " + wsdl + " to call service");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Print number: ");
            String type = input.readLine();
            switch (type) {
                case "1":
                    System.out.println("Calling wsdl... ");
                    crudMenu();
                    break;
                case "2":
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
