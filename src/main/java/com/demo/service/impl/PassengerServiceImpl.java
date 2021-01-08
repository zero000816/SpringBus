package com.demo.service.impl;

import com.demo.entity.Passenger;
import com.demo.repository.PassengerRepository;
import com.demo.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Override
    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger findByPid(int pid) {
        return passengerRepository.findByPid(pid);
    }

    @Override
    public void truncateTable(){
        passengerRepository.truncateTable();
    }
}
