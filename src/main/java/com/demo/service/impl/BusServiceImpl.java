package com.demo.service.impl;


import com.demo.entity.Bus;
import com.demo.entity.Passenger;
import com.demo.repository.BusRepository;
import com.demo.repository.PassengerRepository;
import com.demo.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    BusRepository busRepository;
    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public void setNumber(int number) {
        Bus bus =busRepository.findByBid(1);
        bus.setNumber(number);
        busRepository.save(bus);
    }

    @Override
    public int getNumber() {
        Bus bus =busRepository.findByBid(1);
        return bus.getNumber();
    }

    @Override
    public List<Passenger> getAllPassenger() {
        return passengerRepository.findAll();
    }
}
