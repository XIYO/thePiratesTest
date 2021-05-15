package com.example.demo.db.Vo;

import com.example.demo.db.Dto.BusinessTimesDto;
import com.example.demo.db.entity.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StoreVo {
    private Long storeId;

    private String name;
    private String owner;
    private String description;
    private Integer level;
    private String address;
    private String phone;

    private List<BusinessTimesDto> businessTimesList = new ArrayList<>();

    public StoreVo(Store store, List<BusinessTimesDto> businessTimesDtoList) {
        this.storeId = store.getStoreId();
        this.name = store.getName();
        this.owner = store.getOwner();
        this.description = store.getDescription();
        this.level = store.getLevel();
        this.address = store.getAddress();
        this.phone = store.getPhone();

        if (businessTimesDtoList != null)
            this.businessTimesList = businessTimesDtoList;
    }
}
