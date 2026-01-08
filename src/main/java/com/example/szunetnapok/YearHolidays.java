package com.example.szunetnapok;

import java.util.ArrayList;
import java.util.List;

public class YearHolidays {
    public int year;
    public List<HolidayDay> days;

    public YearHolidays(int year, List<HolidayDay> days) {
        this.year = year;
        this.days = new ArrayList<>();
        this.days = days;
    }

}
