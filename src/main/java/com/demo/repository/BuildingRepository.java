package com.demo.repository;

import com.demo.entity.Building;
import com.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> findAll();
    Building findByBuildingID(String buildingID);
}
