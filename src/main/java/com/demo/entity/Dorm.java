package com.demo.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table
@Data
public class Dorm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int did;

    @Column(unique = true)
    String dormName;

    //所属楼 13
    @Column
    String building;

    //所属楼层 5
    @Column
    int floor;

    @Column
    int totalBed;

    @Column
    int emptyBed;

    @Column
    int gender;

}
