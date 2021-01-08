package com.demo.repository;

import com.demo.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {
    List<Passenger> findAll();
    Passenger findByPid(int pid);

    @Transactional
    @Modifying
    @Query(value = "truncate table passenger",nativeQuery = true)
    void truncateTable();
}
