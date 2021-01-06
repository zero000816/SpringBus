package com.demo.entity;

import com.demo.entity.vo.StudentVo;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int sid;
    //学号(在前端逻辑里可能是workID）
    @Column
    String studentID;
    @Column
    String name;
    @Column
    int status;
    @Column
    String verifyCode;

   /* public Student(StudentVo student, Dorm dorm) {
        this.studentID = student.getStudentID();
        this.name = student.getName();
        this.gender = student.getGender();
        this.dormName = student.getDormName();
        this.verifyCode = student.getVerifyCode();
    }*/

    public Student() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", studentID='" + studentID + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}