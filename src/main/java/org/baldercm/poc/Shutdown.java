package org.baldercm.poc;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;

public class Shutdown {

    public static void main(String[] args) throws Exception {
        try {
            URL url = new URL("http://localhost:8080/shutdown?token=stop_jetty");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.getResponseCode();
        } catch (SocketException e) {
            // Okay - the server is not running
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
