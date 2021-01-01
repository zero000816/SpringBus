package com.demo.entity.vo;

import lombok.Data;

@Data
public class StudentVo {
    String studentID;
    String name;
    String dormName;
    int gender;
    //默认为0，女生
    String verifyCode;
}
