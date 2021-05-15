package com.example.demo.db.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "businessTimesList")
public class Store {

    @Id
    @GeneratedValue
    private Long storeId;

    private String name;
    private String owner;
    private String description;
    private Integer level;
    private String address;
    private String phone;

    @OneToMany(mappedBy = "store",cascade = CascadeType.ALL)
    private List<BusinessTimes> businessTimesList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Holiday> holidayList = new ArrayList<>();

    public Store(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }

    public Store(String name, String owner, int level) {
        this.name = name;
        this.owner = owner;
        this.level = level;
    }

    public Store(String name, String owner, String description, Integer level, String address, String phone) {
        this.name = name;
        this.owner = owner;
        this.description = description;
        this.level = level;
        this.address = address;
        this.phone = phone;
    }
}
