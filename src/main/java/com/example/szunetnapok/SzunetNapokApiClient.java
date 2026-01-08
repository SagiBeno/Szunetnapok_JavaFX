package com.example.szunetnapok;

import java.util.Date;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;

public class SzunetNapokApiClient {
    public static String baseUrlStr;

    public static YearHolidays getYear(int year) {
        return null; // TODO
    }

    public static YearHolidays getYear() {
        return SzunetNapokApiClient.getYear(new Date().getYear());
    }
}
