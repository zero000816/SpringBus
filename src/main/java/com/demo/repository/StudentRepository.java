package com.demo.repository;

import com.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Long> {
    @Override
    List<Student> findAll();

    List<Student> findAllByDormName(String dormName);

    Student findByStudentID(String studentID);
}
