package com.example.demo.db.repo;

import com.example.demo.db.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    public List<Store> findAllByOrderByLevelAsc();

}
