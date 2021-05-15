package com.example.demo.db.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HollyDayDto {
    // storeId
    Long id;
    List<String> holidays = new ArrayList<>();
}
