package com.demo.repository;

import com.demo.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {
    List<Passenger> findAll();
}
