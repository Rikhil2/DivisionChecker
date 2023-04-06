package org.rikhilkumar;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        long start = System.currentTimeMillis();
        HttpClient client = HttpClient.newHttpClient();
        while ((System.currentTimeMillis() - start) < 999999999) {
            long loopStart = System.currentTimeMillis();

            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI("https://ftc-api.firstinspires.org/v2.0/2022/teams?eventCode=FTCCMP1"))
                    .header("Authorization", getBasicAuthenticationHeader("username", "password"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            boolean hasPrinted = false;
            while ((System.currentTimeMillis() - loopStart) < 5000) {
                if (response.body().toCharArray().length > 79 && !hasPrinted) {
                    System.out.println("DIVISIONS ARE OUT!");
                    hasPrinted = true;
                }
            }
        }
    }

    private static String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}