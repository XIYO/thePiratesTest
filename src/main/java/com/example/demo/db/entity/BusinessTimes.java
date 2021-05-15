package com.example.demo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class BusinessTimes {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long businessTimeId;

    @ManyToOne
    @JoinColumn(name = "storeId")
    @JsonIgnore
    private Store store;

    private String day;
    private LocalDate date;
    private LocalTime open;
    private LocalTime close;

    public BusinessTimes(Store store, LocalDate localDate, LocalTime open, LocalTime close) {
        this.store = store;
        this.open = open;
        this.close = close;
        this.date = localDate;

        String day = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        this.day = day;

    }

    public BusinessTimes(Store store, String day, LocalTime open, LocalTime close) {
        this.store = store;
        this.day = day;
        this.open = open;
        this.close = close;
    }

    public BusinessTimes(Store savedStore, BusinessTimes businessTimes) {
        this.store = savedStore;
        this.day = businessTimes.getDay();
        this.open = businessTimes.getOpen();
        this.close = businessTimes.getClose();
    }

    public void setOpen(String open) {
        int hour = Integer.valueOf(open.substring(0, 2));
        int min;
        if (hour == 24) {
            hour = 23;
            min = 59;
        } else {
            min = Integer.valueOf(open.substring(3));
        }
        this.open = LocalTime.of(
                hour,
                min
        );
    }

    public void setClose(String close) {
        int hour = Integer.valueOf(close.substring(0, 2));
        int min;
        if (hour == 24) {
            hour = 23;
            min = 59;
        } else {
            min = Integer.valueOf(close.substring(3));
        }
        this.close = LocalTime.of(
                hour,
                min
        );
    }
}
