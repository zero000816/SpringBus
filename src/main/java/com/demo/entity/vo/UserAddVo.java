package com.demo.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserAddVo {
    private String adminWorkID;
    private String workID;
    private String adminPassword;
    private List<String> roles;
    private String password;
    private String name;
}
