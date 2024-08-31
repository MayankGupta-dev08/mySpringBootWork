package dev.mayank.infinityschoolhouse.controller;

import dev.mayank.infinityschoolhouse.model.Holiday;
import dev.mayank.infinityschoolhouse.util.HolidayType;
import dev.mayank.infinityschoolhouse.util.Holidays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@SuppressWarnings("unused")
public class HolidaysController {
    private List<Holiday> hList = new ArrayList<>();

    public HolidaysController() {
        hList.add(new Holiday(Holidays.REPUBLIC_DAY.getValue(), LocalDate.of(2024, 1, 26), HolidayType.NATIONAL));
        hList.add(new Holiday(Holidays.HOLI.getValue(), LocalDate.of(2024, 3, 25), HolidayType.FESTIVAL));
        hList.add(new Holiday(Holidays.GOOD_FRIDAY.getValue(), LocalDate.of(2024, 3, 29), HolidayType.RELIGIOUS));
        hList.add(new Holiday(Holidays.ID_UL_FITR.getValue(), LocalDate.of(2024, 4, 11), HolidayType.RELIGIOUS));
        hList.add(new Holiday(Holidays.RAM_NAVMI.getValue(), LocalDate.of(2024, 4, 17), HolidayType.RELIGIOUS));
        hList.add(new Holiday(Holidays.MAHAVIR_JAYANTI.getValue(), LocalDate.of(2024, 4, 21), HolidayType.RELIGIOUS));
        hList.add(new Holiday(Holidays.BUDDHA_PURNIMA.getValue(), LocalDate.of(2024, 5, 23), HolidayType.RELIGIOUS));
        hList.add(new Holiday(Holidays.ID_UL_ZUHA.getValue(), LocalDate.of(2024, 6, 17), HolidayType.RELIGIOUS));
        hList.add(new Holiday(Holidays.MUHARRAM.getValue(), LocalDate.of(2024, 7, 17), HolidayType.RELIGIOUS));
        hList.add(new Holiday(Holidays.INDEPENDENCE_DAY.getValue(), LocalDate.of(2024, 8, 15), HolidayType.NATIONAL));
        hList.add(new Holiday(Holidays.JANAMASHTAMI.getValue(), LocalDate.of(2024, 8, 26), HolidayType.RELIGIOUS));
        hList.add(new Holiday(Holidays.ID_E_MILAD.getValue(), LocalDate.of(2024, 9, 16), HolidayType.RELIGIOUS));
        hList.add(new Holiday(Holidays.GANDHI_JAYANTI.getValue(), LocalDate.of(2024, 10, 2), HolidayType.NATIONAL));
        hList.add(new Holiday(Holidays.DUSSEHRA.getValue(), LocalDate.of(2024, 10, 12), HolidayType.FESTIVAL));
        hList.add(new Holiday(Holidays.DIWALI.getValue(), LocalDate.of(2024, 10, 31), HolidayType.FESTIVAL));
        hList.add(new Holiday(Holidays.GURU_NANAK_S_BIRTHDAY.getValue(), LocalDate.of(2024, 11, 15), HolidayType.RELIGIOUS));
        hList.add(new Holiday(Holidays.CHRISTMAS.getValue(), LocalDate.of(2024, 12, 25), HolidayType.RELIGIOUS));
    }

    @GetMapping("/holidays/{display}")
    public String getAllHolidays(@PathVariable String display, Model model) {
        HolidayType holidayType = HolidayType.fromString(display);

        if (null != holidayType) {
            switch (holidayType) {
                case ALL:
                    model.addAttribute("national", true);
                    model.addAttribute("religious", true);
                    model.addAttribute("festival", true);
                    break;
                case NATIONAL:
                    model.addAttribute("national", true);
                    break;
                case RELIGIOUS:
                    model.addAttribute("religious", true);
                    break;
                case FESTIVAL:
                    model.addAttribute("festival", true);
                    break;
            }
        }

        HolidayType[] holidayTypes = HolidayType.values();
        for (HolidayType hT : holidayTypes) {
            model.addAttribute(hT.name(),
                    hList.stream().filter(h -> h.getType().equals(hT)).toArray());
        }
        return "holidays.html";
    }
}