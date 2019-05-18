package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WebServiceClient {

    public static void main(String[] args)  {
        System.out.println("============Registration and search service in the registry jUDDI===========");
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            Menu menu = new Menu(input);
            menu.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
