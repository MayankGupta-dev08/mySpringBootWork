package dev.mayank.infinityschoolhouse.util;

public enum Holidays {
    REPUBLIC_DAY("Republic Day"),
    HOLI("Holi"),
    GOOD_FRIDAY("Good Friday"),
    ID_UL_FITR("Id-ul-Fitr"),
    RAM_NAVMI("Ram Navmi"),
    MAHAVIR_JAYANTI("Mahavir Jayanti"),
    BUDDHA_PURNIMA("Buddha Purnima"),
    ID_UL_ZUHA("Id-ul-Zuha"),
    MUHARRAM("Muharram"),
    CHRISTMAS("Christmas"),
    INDEPENDENCE_DAY("Independence Day"),
    JANAMASHTAMI("Janamashtami"),
    ID_E_MILAD("Id-e-Milad"),
    GANDHI_JAYANTI("Mahatma Gandhi Jayanti"),
    DUSSEHRA("Dussehra"),
    DIWALI("Diwali"),
    GURU_NANAK_S_BIRTHDAY("Guru Nanak’s Birthday");

    private final String value;

    Holidays(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
