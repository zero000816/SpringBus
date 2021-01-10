package com.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int pid;
    @Column
    String name;
    @Column(unique = true)
    String workID;
}
