package com.demo.controller;

import com.demo.entity.Passenger;
import com.demo.mq.producer.Producer;
import com.demo.service.impl.BusServiceImpl;
import com.demo.service.impl.PassengerServiceImpl;
import com.demo.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class StudentController {

    @Autowired
    BusServiceImpl busService;

    @Autowired
    Producer producer;
    @Autowired
    PassengerServiceImpl passengerService;

    @CrossOrigin
    @PostMapping(value = "/student/test")
    @ResponseBody
    public Map test() {
        HashMap<String, Object> response = new HashMap<>();
        try {
            if (busService.getNumber() <= 0) {
                Logger.getGlobal().info("由缓存可知，已无库存");
                response.put("code", 100);
            } else {
                int num = busService.createOrder();

                //这段应该发给mq。。让mq来处理
                Passenger passenger = new Passenger();
                passenger.setWorkID(String.valueOf(num));
                producer.addToMq(passenger);
                Logger.getGlobal().info("购买成功，剩余库存为: [{}]" + num);
            }
        } catch (Exception e) {
            Logger.getGlobal().info("失败");

        }
        response.put("code", 20000);
        return response;
    }

    @CrossOrigin
    @GetMapping(value = "/student/order")
    @ResponseBody
    public Map order(@RequestHeader("X-token") String token) {
        HashMap<String, Object> response = new HashMap<>();
        String name = Token.getUsernameFromToken(token);
        String workID = Token.getWorkIDFromToken(token);

        try {
            int count = busService.addUserCount(workID);
            Logger.getGlobal().info(workID+"已经提交"+count+"次");
            boolean isBanned = busService.getUserIsBanned(workID,0);
            if (isBanned) {
                response.put("message","您已预约，请稍后");
            }
            else if (busService.getNumber() <= 0) {
                Logger.getGlobal().info("由缓存可知，已无库存");
                response.put("code", 100);
            } else {
                int num = busService.createOrder();
                Passenger passenger = new Passenger();
                passenger.setName(name);
                passenger.setWorkID(workID);
                producer.addToMq(passenger);
                Logger.getGlobal().info("购买成功，剩余库存为: [{" + (num - 1) + "}]");
                response.put("message", "提交成功");
                response.put("code", 20000);
            }
        } catch (Exception e) {
            Logger.getGlobal().info("失败");
            response.put("message", "提交失败");
            response.put("code", 2000);
        }
        return response;
    }

    @CrossOrigin
    @GetMapping(value = "/student/information")
    @ResponseBody
    public Map info(@RequestHeader("X-token") String token) {
        HashMap<String, Object> response = new HashMap<>();
        String workID = Token.getWorkIDFromToken(token);
        if (passengerService.findByWorkID(workID) != null) {
            response.put("data", "成功");
        } else {
            response.put("data", "未成功");
        }
        response.put("code", 20000);

        return response;
    }





}
