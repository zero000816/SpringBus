package com.demo.service;

import com.demo.entity.Student;

import java.util.List;

public interface StudentService {
    public void addOrUpdateStudent(Student student);

    List<Student> getAllStudents();

    List<Student> findAllStudentsByDormName(String dormName);
    Student findByStudentID(String studentID);
    boolean ChooseForm(String buildingID, int gender,String firstStudent, List<String> studentIDs);

}
