package com.example.demo.db.repo;

import com.example.demo.db.entity.BusinessTimes;
import com.example.demo.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessTimesRepository extends JpaRepository<BusinessTimes, Long> {

    Optional<BusinessTimes> findByStoreAndDate(Store store, LocalDate date);

    List<BusinessTimes> findAllByStoreAndDateGreaterThanEqualAndDateIsLessThan(Store store, LocalDate currentDate, LocalDate lastDate);
}
