package com.example.szunetnapok;

import java.time.LocalDate;

public class HolidayDay {
    public LocalDate date;
    public String name;
    public int type;
    public int weekday;

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public HolidayDay(LocalDate date, String name, int type, int weekday) {
        this.setDate(date);
        this.setName(name);
        this.setType(type);
        this.setWeekday(weekday);
    }
}
