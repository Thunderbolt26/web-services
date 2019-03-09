package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WebServiceClient {

    public static void main(String[] args)  {
        System.out.println("============People Service===========");
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            Menu menu = new Menu(input);
            menu.execute();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
