package com.example.szunetnapok;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import org.json.*;

public class SzunetNapokApiClient {
    public static String baseUrlStr = "https://szunetnapok.hu/api/";

    public static String getApiKey() throws FileNotFoundException {
        Scanner fs = new Scanner(new FileReader("szunetnapok-api.key"));
        return fs.nextLine();
    }

    public static YearHolidays getYear(int year) throws IOException, InterruptedException {
        YearHolidays result = new YearHolidays(year, new ArrayList<>());

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

        if (!jsonObject.has("message")) {
            JSONArray daysArr = jsonObject.getJSONArray("days");

            for (int i = 0; i < daysArr.length(); i++) {
                JSONObject dayObject = daysArr.getJSONObject(i);
                LocalDate date = LocalDate.parse(dayObject.get("date").toString());
                String name = dayObject.getString("name");
                int type = Integer.parseInt(dayObject.getString("type"));
                int weekday = Integer.parseInt(dayObject.getString("weekday"));
                HolidayDay holidayDay = new HolidayDay(date, name, type, weekday);
                result.days.add(holidayDay);
            }
        }

        return result;
    }

    public static YearHolidays getYear() throws IOException, InterruptedException {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        return SzunetNapokApiClient.getYear(year);
    }
}
