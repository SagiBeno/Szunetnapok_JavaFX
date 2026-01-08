package com.example.szunetnapok;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyTests {
    @Before
    public void testBefore () {
        SzunetnapokApplication.isRunningTest = true;
    }

    @Test
    public void applicationTest() throws IOException {
        SzunetnapokApplication application = new SzunetnapokApplication();
        application.start(null);
    }

    @Test
    public void testHolidayDayClass () {
        LocalDate date = LocalDate.parse("2026-01-01");
        String name = "Újév";
        int type = 1;
        int weekday = 4;

        HolidayDay day = new HolidayDay(date, name, type, weekday);

        Assert.assertEquals(date, day.getDate());
        Assert.assertEquals(name, day.getName());
        Assert.assertEquals(type, day.getType());
        Assert.assertEquals(weekday, day.getWeekday());
    }

    @Test
    public void testYearHolidaysClass () {
        int year = 2026;

        LocalDate date1 = LocalDate.parse("2026-01-01");
        String name1 = "Újév";
        int type1 = 1;
        int weekday1 = 4;

        LocalDate date2 = LocalDate.parse("2025-12-26");
        String name2 = "Karácsony";
        int type2 = 2;
        int weekday2 = 5;

        List<HolidayDay> holidayDayList = new ArrayList<>();
        holidayDayList.add(new HolidayDay(date1, name1, type1, weekday1));
        holidayDayList.add(new HolidayDay(date2, name2, type2, weekday2));

        YearHolidays yearHolidays = new YearHolidays(year, holidayDayList);

        System.out.println(yearHolidays.getDays());

        Assert.assertEquals(year, yearHolidays.getYear());
        Assert.assertEquals(date1, yearHolidays.getDays().getFirst().getDate());
        Assert.assertEquals(name1, yearHolidays.getDays().getFirst().getName());
        Assert.assertEquals(type1, yearHolidays.getDays().getFirst().getType());
        Assert.assertEquals(weekday1, yearHolidays.getDays().getFirst().getWeekday());

        Assert.assertEquals(date2, yearHolidays.getDays().get(1).getDate());
        Assert.assertEquals(name2, yearHolidays.getDays().get(1).getName());
        Assert.assertEquals(type2, yearHolidays.getDays().get(1).getType());
        Assert.assertEquals(weekday2, yearHolidays.getDays().get(1).getWeekday());
    }

    @Test
    public void testSzunetnapokApiClientWithCurrentYear() throws IOException, InterruptedException {
        LocalDate localDate = LocalDate.now();
        int testYear = localDate.getYear();
        YearHolidays yearHolidays = SzunetNapokApiClient.getYear(testYear);
        Assert.assertNotEquals(0, yearHolidays.getDays().size());
    }

    @Test
    public void testSzunetnapokApiClientWithLaterYears() throws IOException, InterruptedException {
        LocalDate localDate = LocalDate.now();
        int testYear = localDate.getYear() + 20;
        YearHolidays yearHolidays = SzunetNapokApiClient.getYear(testYear);
        Assert.assertEquals(0, yearHolidays.getDays().size());
    }

    @Test
    public void testSzunetnapokApiClientWithoutYear() throws IOException, InterruptedException {
        YearHolidays yearHolidays = SzunetNapokApiClient.getYear();
        Assert.assertNotEquals(0, yearHolidays.getDays().size());
    }

    @Test
    public void testWeekday0 () {
        SzunetnapokController controller = new SzunetnapokController();
        LocalDate date = LocalDate.parse("2025-12-29");
        String name = "Test";
        int type = 1;
        int weekday = 0;

        HolidayDay day = new HolidayDay(date, name, type, weekday);

        String weekdayStr = controller.weekdayToText(day.weekday);
        Assert.assertEquals("Ismeretlen", weekdayStr);
    }

    @Test
    public void testWeekday1 () {
        SzunetnapokController controller = new SzunetnapokController();
        LocalDate date = LocalDate.parse("2025-12-29");
        String name = "Test";
        int type = 1;
        int weekday = 1;

        HolidayDay day = new HolidayDay(date, name, type, weekday);

        String weekdayStr = controller.weekdayToText(day.weekday);
        Assert.assertEquals("Hétfő", weekdayStr);
    }

    @Test
    public void testWeekday2 () {
        SzunetnapokController controller = new SzunetnapokController();
        LocalDate date = LocalDate.parse("2025-12-30");
        String name = "Test";
        int type = 1;
        int weekday = 2;

        HolidayDay day = new HolidayDay(date, name, type, weekday);

        String weekdayStr = controller.weekdayToText(day.weekday);
        Assert.assertEquals("Kedd", weekdayStr);
    }

    @Test
    public void testWeekday3 () {
        SzunetnapokController controller = new SzunetnapokController();
        LocalDate date = LocalDate.parse("2025-12-31");
        String name = "Test";
        int type = 1;
        int weekday = 3;

        HolidayDay day = new HolidayDay(date, name, type, weekday);

        String weekdayStr = controller.weekdayToText(day.weekday);
        Assert.assertEquals("Szerda", weekdayStr);
    }

    @Test
    public void testWeekday4 () {
        SzunetnapokController controller = new SzunetnapokController();
        LocalDate date = LocalDate.parse("2026-01-01");
        String name = "Újév";
        int type = 1;
        int weekday = 4;

        HolidayDay day = new HolidayDay(date, name, type, weekday);

        String weekdayStr = controller.weekdayToText(day.weekday);
        Assert.assertEquals("Csütörtök", weekdayStr);
    }

    @Test
    public void testWeekday5 () {
        SzunetnapokController controller = new SzunetnapokController();
        LocalDate date = LocalDate.parse("2026-01-02");
        String name = "Test";
        int type = 1;
        int weekday = 5;

        HolidayDay day = new HolidayDay(date, name, type, weekday);

        String weekdayStr = controller.weekdayToText(day.weekday);
        Assert.assertEquals("Péntek", weekdayStr);
    }

    @Test
    public void testWeekday6 () {
        SzunetnapokController controller = new SzunetnapokController();
        LocalDate date = LocalDate.parse("2026-01-03");
        String name = "Test";
        int type = 1;
        int weekday = 6;

        HolidayDay day = new HolidayDay(date, name, type, weekday);

        String weekdayStr = controller.weekdayToText(day.weekday);
        Assert.assertEquals("Szombat", weekdayStr);
    }

    @Test
    public void testWeekday7 () {
        SzunetnapokController controller = new SzunetnapokController();
        LocalDate date = LocalDate.parse("2026-01-04");
        String name = "Test";
        int type = 1;
        int weekday = 7;

        HolidayDay day = new HolidayDay(date, name, type, weekday);

        String weekdayStr = controller.weekdayToText(day.weekday);
        Assert.assertEquals("Vasárnap", weekdayStr);
    }

    @Test
    public void testTypeToText0 () {
        SzunetnapokController controller = new SzunetnapokController();
        LocalDate date = LocalDate.parse("2026-01-04");
        String name = "Test";
        int type = 0;
        int weekday = 7;

        HolidayDay day = new HolidayDay(date, name, type, weekday);

        String typeStr = controller.typeToText(day.type);
        Assert.assertEquals("Ismeretlen", typeStr);
    }

    @Test
    public void testTypeToText1() {
        SzunetnapokController controller = new SzunetnapokController();
        LocalDate date = LocalDate.parse("2026-01-04");
        String name = "Test";
        int type = 1;
        int weekday = 7;

        HolidayDay day = new HolidayDay(date, name, type, weekday);

        String typeStr = controller.typeToText(day.type);
        Assert.assertEquals("Munkaszüneti nap", typeStr);
    }

    @Test
    public void testTypeToText2() {
        SzunetnapokController controller = new SzunetnapokController();
        LocalDate date = LocalDate.parse("2026-01-04");
        String name = "Test";
        int type = 2;
        int weekday = 7;

        HolidayDay day = new HolidayDay(date, name, type, weekday);

        String typeStr = controller.typeToText(day.type);
        Assert.assertEquals("Munkanap", typeStr);
    }

}
