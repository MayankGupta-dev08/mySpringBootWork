package dev.mayank.infinityschoolhouse.controller;

import dev.mayank.infinityschoolhouse.model.Holiday;
import dev.mayank.infinityschoolhouse.repository.HolidayRepository;
import dev.mayank.infinityschoolhouse.util.HolidayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@SuppressWarnings("unused")
public class HolidaysController {
    private final HolidayRepository holidayRepository;

    @Autowired
    public HolidaysController(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @GetMapping("/holidays/{display}")
    public String getAllHolidays(@PathVariable String display, Model model) {
        List<Holiday> allHolidays = holidayRepository.findAll();
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
                    allHolidays.stream().filter(h -> h.getType().equals(hT)).toArray());
        }
        return "holidays.html";
    }
}