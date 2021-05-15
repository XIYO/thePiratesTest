package com.example.demo.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
//@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor


public class Holiday {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long holidayId;

    private LocalDate holiday;

    @ManyToOne
    @JoinColumn(name = "storeId")
    @JsonIgnore
    private Store store;

    public Holiday(Store store, String date) {
        String test = date.substring(5, 8);
        int year = Integer.valueOf(date.substring(0, 4));
        int month = Integer.valueOf(date.substring(5, 7));
        int day = Integer.valueOf(date.substring(8));

        LocalDate localDate = LocalDate.of(year, month, day);

        this.store = store;
        this.holiday = localDate;
    }

    public Holiday(Store store, LocalDate localDate) {
        this.store = store;
        this.holiday = localDate;
    }
}
