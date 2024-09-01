package dev.mayank.infinityschoolhouse.util;

import jdk.jfr.Description;

@Description("Constants for the 'holidays' table")
public final class HTConstants extends TableConstants {
    public static final String TABLE_NAME = "holidays";
    public static final String COLUMN_ID = "holiday_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TYPE = "type";

    private HTConstants() {
        super();
    }
}