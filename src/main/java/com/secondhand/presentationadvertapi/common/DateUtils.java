package com.secondhand.presentationadvertapi.common;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;

import org.springframework.lang.NonNull;

public final class DateUtils {
    public static final ZoneOffset TURKEY_ZONE_OFFSET = ZoneOffset.ofHours(3);
    private static final DateTimeFormatter DATE_TIME_FORMATTER_HOUR_AND_MINUTE = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_TIME_FORMATTER_DAY_MONTH_IN_TR;
    private static final DateTimeFormatter DATE_TIME_FORMATTER_DAY_MONTH_YEAR_IN_TR;

    private DateUtils() {
    }

    @NonNull
    private static String format(TemporalAccessor temporal, @NonNull DateTimeFormatter formatter) {
        Objects.requireNonNull(formatter);
        return temporal == null ? "" : formatter.format(temporal);
    }

    @NonNull
    public static String formatWithISOLocalDate(LocalDateTime localDateTime) {
        return format(localDateTime, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @NonNull
    public static String formatWithISOLocalDate(ZonedDateTime zonedDateTime) {
        return format(zonedDateTime, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @NonNull
    public static String formatWithISOOffsetDate(ZonedDateTime zonedDateTime) {
        return format(zonedDateTime, DateTimeFormatter.ISO_OFFSET_DATE);
    }

    @NonNull
    public static String formatWithISOLocalTime(LocalDateTime localDateTime) {
        return format(localDateTime, DateTimeFormatter.ISO_LOCAL_TIME);
    }

    @NonNull
    public static String formatWithISOLocalTime(ZonedDateTime zonedDateTime) {
        return format(zonedDateTime, DateTimeFormatter.ISO_LOCAL_TIME);
    }

    @NonNull
    public static String formatWithISOOffsetTime(ZonedDateTime zonedDateTime) {
        return format(zonedDateTime, DateTimeFormatter.ISO_OFFSET_TIME);
    }

    @NonNull
    public static String formatWithISOLocalDateTime(LocalDateTime localDateTime) {
        return format(localDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @NonNull
    public static String formatWithISOLocalDateTime(ZonedDateTime zonedDateTime) {
        return format(zonedDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @NonNull
    public static String formatWithISOOffsetDateTime(ZonedDateTime zonedDateTime) {
        return format(zonedDateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    @NonNull
    public static String formatWithISOInstant(ZonedDateTime zonedDateTime) {
        return format(zonedDateTime, DateTimeFormatter.ISO_INSTANT);
    }

    @NonNull
    public static String formatWithHourAndMinute(LocalDateTime localDateTime) {
        return format(localDateTime, DATE_TIME_FORMATTER_HOUR_AND_MINUTE);
    }

    @NonNull
    public static String formatWithHourAndMinute(ZonedDateTime zonedDateTime) {
        return format(zonedDateTime, DATE_TIME_FORMATTER_HOUR_AND_MINUTE);
    }

    @NonNull
    public static String formatWithDayAndMonthInTurkish(LocalDateTime localDateTime) {
        return format(localDateTime, DATE_TIME_FORMATTER_DAY_MONTH_IN_TR);
    }

    @NonNull
    public static String formatWithDayAndMonthInTurkish(ZonedDateTime zonedDateTime) {
        return format(zonedDateTime, DATE_TIME_FORMATTER_DAY_MONTH_IN_TR);
    }

    @NonNull
    public static String formatWithDayAndMonthAndYearInTurkish(LocalDateTime localDateTime) {
        return format(localDateTime, DATE_TIME_FORMATTER_DAY_MONTH_YEAR_IN_TR);
    }

    @NonNull
    public static String formatWithDayAndMonthAndYearInTurkish(ZonedDateTime zonedDateTime) {
        return format(zonedDateTime, DATE_TIME_FORMATTER_DAY_MONTH_YEAR_IN_TR);
    }

    static {
        DATE_TIME_FORMATTER_DAY_MONTH_IN_TR = DateTimeFormatter.ofPattern("dd MMMM", LocaleUtils.TR_LOCALE);
        DATE_TIME_FORMATTER_DAY_MONTH_YEAR_IN_TR = DateTimeFormatter.ofPattern("dd MMMM yyyy", LocaleUtils.TR_LOCALE);
    }
}
