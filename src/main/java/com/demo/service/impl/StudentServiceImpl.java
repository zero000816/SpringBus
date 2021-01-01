package com.demo.service.impl;

import com.demo.entity.ChooseLog;
import com.demo.entity.Dorm;
import com.demo.entity.Student;
import com.demo.repository.ChooseLogRepository;
import com.demo.repository.DormRepository;
import com.demo.repository.StudentRepository;
import com.demo.service.StudentService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    DormRepository dormRepository;
    @Autowired
    ChooseLogRepository chooseLogRepository;

    @Override
    public void addOrUpdateStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findAllStudentsByDormName(String dormName) {
        return studentRepository.findAllByDormName(dormName);
    }

    @Override
    public Student findByStudentID(String studentID) {
        return studentRepository.findByStudentID(studentID);
    }

    @Override
    public boolean ChooseForm(String buildingID, int gender, String firstStudent, List<String> studentIDs) {
        boolean flag=false;
        System.out.println("分配宿舍");
        System.out.println(studentIDs.get(0));
        Student student = studentRepository.findByStudentID(studentIDs.get(0));
        if (!student.getDormName().equals("")&&student.getDormName()!=null) {
            System.out.println("已分配");
        } else {
            ChooseLog log=new ChooseLog();
            log.setStudentID(studentIDs.get(0));
            log.setInformation("true");
            chooseLogRepository.save(log);
            int numOfStudent = studentIDs.size();
            System.out.println(numOfStudent);
            List<Dorm> dormList = dormRepository.findAllByBuilding(buildingID);
            System.out.println(dormList);
            for (Dorm dorm : dormList) {
                if (dorm.getEmptyBed() > numOfStudent + 1) {
                    for (String id : studentIDs) {
                        Student studentTemp = studentRepository.findByStudentID(id);
                        studentTemp.setDormName(dorm.getDormName());
                        studentRepository.save(studentTemp);
                        System.out.println(studentTemp.getDormName());

                    }
                    dorm.setEmptyBed(dorm.getEmptyBed() - numOfStudent);
                    dormRepository.save(dorm);
                    flag=true;
                    break;
                }
            }
        }
        return flag;
    }
}

