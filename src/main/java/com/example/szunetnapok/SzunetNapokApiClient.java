package com.example.szunetnapok;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;

import org.json.*;

public class SzunetNapokApiClient {
    public static String baseUrlStr = "https://szunetnapok.hu/api/";

    public static String getApiKey() throws FileNotFoundException {
        Scanner fs = new Scanner(new FileReader("szunetnapok-api.key"));
        return fs.nextLine();
    }

    public static YearHolidays getYear(int year) throws IOException, InterruptedException {
        String apiKey = getApiKey();

        String method = "GET";
        String endpoint = baseUrlStr + apiKey + "/" + year + "/json/";

        HttpClient http = HttpClient.newBuilder().build();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .GET()
                .build();

        HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(res.statusCode());
        System.out.println(res.body());

        String json = res.body();
        JSONObject jsonObject = new JSONObject(json);
        JSONArray daysArr = jsonObject.getJSONArray("days");
        for (Object day : daysArr) {
            JSONObject dayObject = new JSONObject(day);

        }

        return null; // TODO
    }

    public static YearHolidays getYear() throws IOException, InterruptedException {
        return SzunetNapokApiClient.getYear(new Date().getYear());
    }
}
