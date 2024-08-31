package dev.mayank.infinityschoolhouse.util;

public enum HolidayType {
    ALL, NATIONAL, RELIGIOUS, FESTIVAL;

    public static HolidayType fromString(String display) {
        try {
            return HolidayType.valueOf(display.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }
}
