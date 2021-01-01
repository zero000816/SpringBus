package com.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ChooseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int clid;

    @Column
    String studentID;

    @Column
    String information;
}
