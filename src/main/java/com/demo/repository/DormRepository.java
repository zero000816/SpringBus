package com.demo.repository;

import com.demo.entity.Dorm;
import com.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DormRepository extends JpaRepository<Dorm, Long> {
    List<Dorm> findAllByBuilding(String building);
    Dorm findByDid(int did);
    Dorm findByDormName(String dormName);

}
