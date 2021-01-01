package com.demo.repository;

import com.demo.entity.ChooseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChooseLogRepository extends JpaRepository<ChooseLog, Long> {
    ChooseLog findAllByStudentID(String studentID);
}
