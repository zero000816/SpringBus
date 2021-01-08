package com.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bid;

    //number 表示总数
    @Column
    private Integer number;

    @Column
    private Integer sale=0;

}
