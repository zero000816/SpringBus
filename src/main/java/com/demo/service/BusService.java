package com.demo.service;

import com.demo.entity.Bus;
import com.demo.entity.Passenger;

import java.util.List;

public interface BusService {
    void setNumber(int number);
    int getNumberFromBD();
    int getNumberFromRedis();
    int getNumber();
    List<Passenger> getAllPassenger();
    int createOrder();
    boolean getUserIsBanned(String workID,int num);
    public int addUserCount(String workID);
}
