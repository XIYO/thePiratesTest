package com.example.demo.db.Dto;

import com.example.demo.db.entity.BusinessTimes;
import com.example.demo.db.entity.Holiday;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class BusinessTimesDto {

    String day;
    LocalTime open;
    LocalTime close;

    String status = "CLOSE";

    public BusinessTimesDto(BusinessTimes businessTimes, List<Holiday> holidayList) {
        if (businessTimes != null) {
            this.day = businessTimes.getDay();
            this.open = businessTimes.getOpen();
            this.close = businessTimes.getClose();
        }

        LocalTime currentTime = LocalTime.now();
        LocalDate currentDate = LocalDate.now();
        for (Holiday holiday : holidayList) {
            if (businessTimes.getDate().equals(holiday.getHoliday())) {
                this.status = "HOLIDAY";
                break;
            } else if (businessTimes.getDate().equals(currentDate)) {
                if (businessTimes.getOpen().isBefore(currentTime)) {
                    if (businessTimes.getClose().isAfter(currentTime)) {
                        status = "OPEN";
                        break;
                    }
                }
                break;
            }
        }
    }
}
