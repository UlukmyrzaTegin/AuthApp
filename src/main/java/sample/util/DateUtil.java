package sample.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * TheSusanin
 * 3/21/2021
 */
public class DateUtil {
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    /** Форматировщик даты. */
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return DATE_FORMATTER.format(date);
    }

    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean validDate(String dateString) {
        // Пытаемся разобрать строку.
        return DateUtil.parse(dateString) != null;
    }
}
