package com.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class RoleAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int raid;
    @Column(name = "role_id")
    int roleId;
    @Column(name = "access_id")
    int accessId;


}
