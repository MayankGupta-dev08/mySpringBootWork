package dev.mayank.infinityschoolhouse.controller;

import dev.mayank.infinityschoolhouse.model.Holiday;
import dev.mayank.infinityschoolhouse.model.Holiday.HolidayType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@SuppressWarnings("unused")
public class HolidaysController {
    private static final String REPUBLIC_DAY = "Republic Day";
    private static final String HOLI = "Holi";
    private static final String GOOD_FRIDAY = "Good Friday";
    private static final String ID_UL_FITR = "Id-ul-Fitr";
    private static final String RAM_NAVMI = "Ram Navmi";
    private static final String MAHAVIR_JAYANTI = "Mahavir Jayanti";
    private static final String BUDDHA_PURNIMA = "Buddha Purnima";
    private static final String ID_UL_ZUHA_BAKRID = "Id-ul-Zuha (Bakrid)";
    private static final String MUHARRAM = "Muharram";
    private static final String CHRISTMAS = "Christmas";
    private static final String INDEPENDENCE_DAY = "Independence Day";
    private static final String JANAMASHTAMI = "Janamashtami";
    private static final String ID_E_MILAD = "Id-e-Milad";
    private static final String GANDHI_JAYANTI = "Mahatma Gandhi Jayanti";
    private static final String DUSSEHRA = "Dussehra";
    private static final String DIWALI = "Diwali";
    private static final String GURU_NANAK_S_BIRTHDAY = "Guru Nanak’s Birthday";

    private List<Holiday> holidays = new ArrayList<>();

    public HolidaysController() {
        holidays.add(new Holiday(REPUBLIC_DAY, LocalDate.of(2024, 1, 26), HolidayType.NATIONAL));
        holidays.add(new Holiday(HOLI, LocalDate.of(2024, 3, 25), HolidayType.FESTIVAL));
        holidays.add(new Holiday(GOOD_FRIDAY, LocalDate.of(2024, 3, 29), HolidayType.RELIGIOUS));
        holidays.add(new Holiday(ID_UL_FITR, LocalDate.of(2024, 4, 11), HolidayType.RELIGIOUS));
        holidays.add(new Holiday(RAM_NAVMI, LocalDate.of(2024, 4, 17), HolidayType.RELIGIOUS));
        holidays.add(new Holiday(MAHAVIR_JAYANTI, LocalDate.of(2024, 4, 21), HolidayType.RELIGIOUS));
        holidays.add(new Holiday(BUDDHA_PURNIMA, LocalDate.of(2024, 5, 23), HolidayType.RELIGIOUS));
        holidays.add(new Holiday(ID_UL_ZUHA_BAKRID, LocalDate.of(2024, 6, 17), HolidayType.RELIGIOUS));
        holidays.add(new Holiday(MUHARRAM, LocalDate.of(2024, 7, 17), HolidayType.RELIGIOUS));
        holidays.add(new Holiday(INDEPENDENCE_DAY, LocalDate.of(2024, 8, 15), HolidayType.NATIONAL));
        holidays.add(new Holiday(JANAMASHTAMI, LocalDate.of(2024, 8, 26), HolidayType.RELIGIOUS));
        holidays.add(new Holiday(ID_E_MILAD, LocalDate.of(2024, 9, 16), HolidayType.RELIGIOUS));
        holidays.add(new Holiday(GANDHI_JAYANTI, LocalDate.of(2024, 10, 2), HolidayType.NATIONAL));
        holidays.add(new Holiday(DUSSEHRA, LocalDate.of(2024, 10, 12), HolidayType.FESTIVAL));
        holidays.add(new Holiday(DIWALI, LocalDate.of(2024, 10, 31), HolidayType.FESTIVAL));
        holidays.add(new Holiday(GURU_NANAK_S_BIRTHDAY, LocalDate.of(2024, 11, 15), HolidayType.RELIGIOUS));
        holidays.add(new Holiday(CHRISTMAS, LocalDate.of(2024, 12, 25), HolidayType.RELIGIOUS));
    }

    @GetMapping("/holidays")
    public String getAllHolidays(Model model) {
        Holiday.HolidayType[] holidayTypes = Holiday.HolidayType.values();
        for (Holiday.HolidayType holidayType : holidayTypes) {
            model.addAttribute(holidayType.name(), holidays.stream()
                    .filter(holiday -> holiday.getType().equals(holidayType)).toArray());
        }
        return "holidays.html";
    }

    @GetMapping("/{name}")
    public Holiday getHolidayByName(@PathVariable String name) {
        return holidays.stream()
                .filter(holiday -> holiday.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    public void addHoliday(@RequestBody Holiday holiday) {
        holidays.add(holiday);
    }

    @PutMapping("/{name}")
    public void updateHoliday(@PathVariable String name, @RequestBody Holiday updatedHoliday) {
        holidays.stream()
                .filter(holiday -> holiday.getName().equalsIgnoreCase(name))
                .findFirst()
                .ifPresent(holiday -> {
                    holidays.remove(holiday);
                    holidays.add(updatedHoliday);
                });
    }

    @DeleteMapping("/{name}")
    public void deleteHoliday(@PathVariable String name) {
        holidays.removeIf(holiday -> holiday.getName().equalsIgnoreCase(name));
    }
}