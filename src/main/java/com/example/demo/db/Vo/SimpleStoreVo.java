package com.example.demo.db.Vo;

import com.example.demo.db.Dto.SimpleStoreDto;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@ToString
public class SimpleStoreVo {
    String name;
    String description;
    int level;
    String businessStatus;


    public SimpleStoreVo(String name, String description, int level) {
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public SimpleStoreVo(SimpleStoreDto simpleStoreDto) {
        this.name = simpleStoreDto.getName();
        this.description = simpleStoreDto.getDescription();
        this.level = simpleStoreDto.getLevel();

        LocalTime currentTime = LocalTime.now();

        if (simpleStoreDto.getHoliday() != null) {
            if (!simpleStoreDto.getHoliday().equals("")) {
                businessStatus = "HOLIDAY";
            } else businessStatus = "CLOSE";
        } else if (simpleStoreDto.getOpen() != null) {
            if (simpleStoreDto.getOpen().isBefore(currentTime) && simpleStoreDto.getClose().isAfter(currentTime)) {
                businessStatus = "OPEN";
            } else businessStatus = "CLOSE";
        } else businessStatus = "CLOSE";

    }
}
