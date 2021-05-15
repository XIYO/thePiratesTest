package com.example.demo.db.Dto;

import com.example.demo.db.entity.BusinessTimes;
import com.example.demo.db.entity.Holiday;
import com.example.demo.db.entity.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class SimpleStoreDto {
    String name;
    String description;
    int level;

    String day;
    LocalTime open;
    LocalTime close;

    LocalDate holiday;

    public SimpleStoreDto(Store store, BusinessTimes businessTimes, Holiday holiday) {
        this.name = store.getName();
        this.description = store.getDescription();
        this.level = store.getLevel();

        if (businessTimes != null) {
            this.day = businessTimes.getDay();
            this.open = businessTimes.getOpen();
            this.close = businessTimes.getClose();
        }

        if (holiday != null)
            this.holiday = holiday.getHoliday();
    }
}
