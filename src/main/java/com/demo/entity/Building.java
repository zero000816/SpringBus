package com.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Table
@Entity
@Data
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bid;
    //楼号
    @Column
    String buildingID;
    //楼层数
    @Column
    int floors;

}
