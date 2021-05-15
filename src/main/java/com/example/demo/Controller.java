package com.example.demo;

import com.example.demo.db.Dto.BusinessTimesDto;
import com.example.demo.db.Dto.HollyDayDto;
import com.example.demo.db.Dto.SimpleStoreDto;
import com.example.demo.db.Vo.SimpleStoreVo;
import com.example.demo.db.Vo.StoreVo;
import com.example.demo.db.entity.BusinessTimes;
import com.example.demo.db.entity.Holiday;
import com.example.demo.db.entity.Store;
import com.example.demo.db.repo.BusinessTimesRepository;
import com.example.demo.db.repo.HolidayRepository;
import com.example.demo.db.repo.StoreRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public record Controller(StoreRepository storeRepository,
                         BusinessTimesRepository businessTimesRepository,
                         HolidayRepository holiDayRepository) {

    @GetMapping(value = "/test")
    public void test() {

        LocalTime localTime = LocalTime.of(7, 30);
        System.out.println(localTime.isBefore(LocalTime.now()));
        System.out.println(localTime.isAfter(LocalTime.now()));

        for (int i = 0; i < 10; i++) {
            Store store = new Store(i + "  수산", i + " 주인 ", "desc", i % 4, "address", "010-1234-1234");

            //businessTime
            for (int j = 0; j < 10; j++) {
                LocalDate localDate = LocalDate.of(2021, 5, j + 15);
                LocalTime open = LocalTime.of(7, 30);
                LocalTime close = LocalTime.of(23, i);
                BusinessTimes businessTimes = new BusinessTimes(store, localDate, open, close);
                store.getBusinessTimesList().add(businessTimes);
            }

            for (int j = 14; j < 30; j = j + 2) {
                LocalDate localDate = LocalDate.of(2021, 5, j);
                Holiday holiday = new Holiday(store, localDate);
                store.getHolidayList().add(holiday);
            }

            storeRepository.save(store);
        }
    }

    @GetMapping(value = "/all")
    public List<Store> all() {
        return storeRepository.findAll();
    }

    // A 점포 추가
    @PostMapping(value = "/addstore")
    public void test(@RequestBody Store store) {
        if (store.getName() != null) {
            Store savedStore = storeRepository.save(store);
            for (BusinessTimes businessTimes : store.getBusinessTimesList()) {
                BusinessTimes temp = new BusinessTimes(savedStore, businessTimes);
                businessTimesRepository.save(temp);
            }
        }
    }

    // B 점포 휴무일 등록
    @PostMapping(value = "/addholiday")
    public void hollyDayPut(@RequestBody HollyDayDto hollyDayDto) {
        Store store = storeRepository.findById(hollyDayDto.getId()).orElse(null);

        if (store != null) {
            for (String localDate : hollyDayDto.getHolidays()) {
                Holiday holiDay = new Holiday(store, localDate);
                store.getHolidayList().add(holiDay);
            }
            storeRepository.save(store);
        }
    }

    // C 점포 목록 조회
    // 현 시간을 기준으로 간단 조회
    // 등급 오름 차순.
    @GetMapping(value = "/simpleget")
    public List<SimpleStoreVo> simpleFindAll() {
        String day = LocalDateTime.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        LocalDate localDate = LocalDate.now();

        List<SimpleStoreVo> simpleStoreVoList = new ArrayList<>();
        List<Store> storeList = storeRepository.findAllByOrderByLevelAsc();
        for (Store store : storeList) {
            BusinessTimes businessTimes = businessTimesRepository.findByStoreAndDate(store, localDate).orElse(null);
            Holiday holiDay = holiDayRepository.findByStoreAndHoliday(store, localDate).orElse(null);

            SimpleStoreDto simpleStoreDto = new SimpleStoreDto(store, businessTimes, holiDay);
            simpleStoreVoList.add(new SimpleStoreVo(simpleStoreDto));
        }
        return simpleStoreVoList;
    }


    // D 점포 상세 정보 조회
    // 현 시간을 기준으로 상세 조회
    // 조회 일자 기준 영업시간 3일치의 의미는 오늘 날짜 이후로 3일 이후의 데이터
    @GetMapping(value = "/storeget")
    public StoreVo findById(long id) {
        if (storeRepository.findById(id).orElse(null) != null) {
            String day = LocalDateTime.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
            LocalDate currentDate = LocalDate.now();
            LocalDate lastDate = LocalDate.now().plusDays(3);

            Store store = storeRepository.findById(id).orElse(null);

            List<BusinessTimes> businessTimesList = businessTimesRepository.findAllByStoreAndDateGreaterThanEqualAndDateIsLessThan(store, currentDate, lastDate);
            List<Holiday> holidayList = holiDayRepository.findAllByStoreAndHolidayGreaterThanEqualAndHolidayLessThan(store, currentDate, lastDate);

            List<BusinessTimesDto> businessTimesDtoList = new ArrayList<>();
            for (BusinessTimes businessTimes : businessTimesList) {
                BusinessTimesDto businessTimesDto = new BusinessTimesDto(businessTimes, holidayList);
                businessTimesDtoList.add(businessTimesDto);
            }

            StoreVo storeVo = new StoreVo(store, businessTimesDtoList);

            return storeVo;
        }
        return null;
    }

    // C 점포 삭제
    @DeleteMapping(value = "/delete")
    public void delete(long id) {
        if (storeRepository.findById(id).orElse(null) != null)
            storeRepository.deleteById(id);
    }

}
