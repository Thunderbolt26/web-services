package service;

import javax.xml.ws.Endpoint;

public class App {

    public static void main(String[] args) {
        String url = "http://0.0.0.0:8080/FootballClubService";
        Endpoint.publish(url, new FootballClubWebService());
    }
}
