package com.demo.service;

import com.demo.entity.Passenger;

import java.util.List;

public interface BusService {
    void setNumber(int number);
    int getNumber();
    List<Passenger> getAllPassenger();
}
