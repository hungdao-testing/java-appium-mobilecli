package auto.mobile.formcliatd.utils;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

    public static LocalDate parseStringAsDatePattern(String date, DateTimeFormatter formatter) {
        return LocalDate.parse(date, formatter);
    }

    public static LocalDate parseStringAsDatePattern(String date) {
        return LocalDate.parse(date);
    }

    public static String getCurrentDate(DateTimeFormatter formatter) {
        return LocalDate.now().toString(formatter);
    }

    public static String getDay(String date, DateTimeFormatter formatter) {
        return String.valueOf(parseStringAsDatePattern(date, formatter).getDayOfMonth());
    }

    public static String getMonthAsText(String date, DateTimeFormatter formatter) {
        return parseStringAsDatePattern(date, formatter).toString(DateTimeFormat.forPattern("MMMM"));
    }

    public static String getMonthAsText(LocalDate date) {
        return date.toString(DateTimeFormat.forPattern("MMMM"));
    }

    public static String getMonthAsShortText(String date, DateTimeFormatter formatter) {
        return parseStringAsDatePattern(date, formatter).toString(DateTimeFormat.forPattern("MMM"));
    }

    public static String getMonth(String date, DateTimeFormatter formatter) {
        return parseStringAsDatePattern(date, formatter).toString(DateTimeFormat.forPattern("M"));
    }

    public static String getFullYear(String date, DateTimeFormatter formatter) {
        return String.valueOf(parseStringAsDatePattern(date, formatter).getYear());
    }
}
