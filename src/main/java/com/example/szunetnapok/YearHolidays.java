package com.example.szunetnapok;

import java.util.ArrayList;
import java.util.List;

public class YearHolidays {
    public int year;
    public List<HolidayDay> days;

    public void setYear(int year) {
        this.year = year;
    }

    public void setDays(List<HolidayDay> days) {
        this.days = days;
    }

    public int getYear() {
        return year;
    }

    public List<HolidayDay> getDays() {
        return days;
    }

    public YearHolidays(int year, List<HolidayDay> days) {
        this.setYear(year);
        this.setDays(new ArrayList<>());
    }

}
