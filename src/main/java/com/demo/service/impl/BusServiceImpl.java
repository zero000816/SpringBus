package com.demo.service.impl;


import com.demo.entity.Bus;
import com.demo.entity.Passenger;
import com.demo.repository.BusRepository;
import com.demo.repository.PassengerRepository;
import com.demo.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service

public class BusServiceImpl implements BusService {

    @Autowired
    BusRepository busRepository;
    @Autowired
    PassengerRepository passengerRepository;
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public void setNumber(int number) {
        Bus bus =busRepository.findByBid(1);
        bus.setNumber(number);
        busRepository.save(bus);
    }

    @Override
    public int getNumberFromBD() {
        Bus bus =busRepository.findByBid(1);
        return bus.getNumber();
    }
    @Override
    public int getNumberFromRedis(){
        String number = redisTemplate.opsForValue().get("number");
        if (number==null||number.length()==0){
            return 1000;
        }
        return Integer.valueOf(number);
    }

    @Override
    public int getNumber() {
        int number=this.getNumberFromRedis();
        Logger.getGlobal().info("得到的缓存的数据"+number);
/*        if(number>0){
            number=this.getNumberFromBD();
            redisTemplate.opsForValue().set("number",String.valueOf(number));
        }*/
        return number;
    }

    @Override
    public List<Passenger> getAllPassenger() {
        return passengerRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor= Exception.class,isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int createOrder(){
        Bus bus=checkSaleForUpdate();
        if(!updateSale(bus)){
            throw new RuntimeException("更新失败");
        }
        redisTemplate.opsForValue().set("number",String.valueOf(bus.getNumber()-1));
        return bus.getNumber();
    }

    public boolean updateSale(Bus bus) {
        int id=busRepository.updateSale(bus.getBid());
        return id!=0;
    }

    public Bus checkSaleForUpdate() {
        Bus bus =busRepository.findByBidForUpdate(1);
        if(bus.getNumber()<=0){
            throw new RuntimeException("库存不足");
        }
        return bus;
    }

    public void clear(){
        redisTemplate.delete("number");
    }

}
