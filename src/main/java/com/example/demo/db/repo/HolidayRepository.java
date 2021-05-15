package com.example.demo.db.repo;

import com.example.demo.db.entity.Holiday;
import com.example.demo.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
    Optional<Holiday> findByStoreAndHoliday(Store store, LocalDate localDate);

    List<Holiday> findAllByStoreAndHolidayGreaterThanEqualAndHolidayLessThan(Store store, LocalDate currentDate, LocalDate lastDate);
}
