package com.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ruid;

    @Column(name = "user_id")
    int userId;

    @Column(name = "role_id")
    int roleId;

    @Column(name = "status")
    int status;
    //1表示不可用，懒惰删除？


}
