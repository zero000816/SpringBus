package com.demo.service;

import com.demo.entity.Passenger;

public interface PassengerService {
    Passenger save(Passenger passenger);
    Passenger findByPid(int pid);
    Passenger findByWorkID(String workID);
    void truncateTable();
}
