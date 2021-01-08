package com.demo.service;

import com.demo.entity.Passenger;

public interface PassengerService {
    Passenger save(Passenger passenger);
    Passenger findByPid(int pid);
    void truncateTable();
}
