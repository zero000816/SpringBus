package com.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;

    @Column
    private int number;
}
