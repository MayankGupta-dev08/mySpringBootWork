package dev.mayank.infinityschoolhouse.model;

import dev.mayank.infinityschoolhouse.util.HolidayType;

import java.time.LocalDate;

public class Holiday {
    private final String name;
    private final LocalDate date;
    private final HolidayType type;

    public Holiday(String name, LocalDate date, HolidayType type) {
        this.name = name;
        this.date = date;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public HolidayType getType() {
        return type;
    }
}
