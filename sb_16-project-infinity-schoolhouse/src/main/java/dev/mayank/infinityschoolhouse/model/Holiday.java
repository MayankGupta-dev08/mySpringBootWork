package dev.mayank.infinityschoolhouse.model;

import dev.mayank.infinityschoolhouse.util.HolidayType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Holiday extends BaseEntity {
    private int id;
    private String name;
    private LocalDate date;
    private HolidayType type;
}