package dev.mayank.infinityschoolhouse.util;

import jdk.jfr.Description;

@Description("Constants for the 'contact_msg' table")
public final class CMTConstants extends TableConstants {
    public static final String TABLE_NAME = "contact_msg";
    public static final String COLUMN_ID = "contact_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_MOBILE = "mobile_num";
    public static final String COLUMN_SUBJECT = "subject";
    public static final String COLUMN_MESSAGE = "message";
    public static final String COLUMN_STATUS = "status";

    private CMTConstants() {
        super();
    }
}