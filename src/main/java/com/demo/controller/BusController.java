package com.demo.controller;

import com.demo.entity.Bus;
import com.demo.entity.Passenger;
import com.demo.entity.User;
import com.demo.entity.vo.BusVo;
import com.demo.mq.producer.Producer;
import com.demo.repository.BusRepository;
import com.demo.service.PassengerService;
import com.demo.service.impl.BusServiceImpl;
import com.demo.service.impl.PassengerServiceImpl;
import com.demo.service.impl.UserServiceImpl;
import com.demo.utils.MD5;
import com.demo.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@Controller
public class BusController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    BusServiceImpl busService;
    @Autowired
    BusRepository busRepository;
    @Autowired
    PassengerServiceImpl passengerService;
    @Autowired
    Producer producer;

    @CrossOrigin
    @PostMapping(value = "/bus/submit")
    @ResponseBody
    public Map submit(@RequestBody BusVo busVo) {
        System.out.println(busVo);
        System.out.println(MD5.encrypt(busVo.getPassword()));
        HashMap<String, Object> response = new HashMap<>();
        User userBack = userService.findByWorkID(busVo.getWorkID());
        if (userBack == null) {
            response.put("code", 2000);
            response.put("message", "失败");
        } else if (userBack.getPassword().equals(MD5.encrypt(busVo.getPassword()))) {
            System.out.println("BUS");
            busService.setNumber(busVo.getNumber());
            response.put("code", 20000);
            response.put("message", "登录成功");
            System.out.println("in");
        } else {
            response.put("message", "登录失败");
        }
        return response;
    }

    @CrossOrigin
    @GetMapping(value = "/bus/list")
    @ResponseBody
    public Map ListPassenger() {
        HashMap<String, Object> response = new HashMap<>();
        Set<Passenger> passengerSet = new HashSet<>();
        for (Passenger passenger : busService.getAllPassenger()) {
            Passenger passengerTemp = new Passenger();
            passengerTemp.setName(passenger.getName());
            passengerTemp.setWorkID(passenger.getWorkID());
            passengerSet.add(passengerTemp);
        }
        response.put("data", passengerSet);
        response.put("code", 20000);
        return response;
    }

    @CrossOrigin
    @PostMapping(value = "/bus/test")
    @ResponseBody
    public Map test() {
        HashMap<String, Object> response = new HashMap<>();
            try {
                if (busService.getNumber() <= 0) {
                    Logger.getGlobal().info("由缓存可知，已无库存");
                    response.put("code", 100);
                }else {
                    int num = busService.createOrder();

                    //这段应该发给mq。。让mq来处理
                    Passenger passenger=new Passenger();
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
    @GetMapping(value = "/bus/order")
    @ResponseBody
    public Map order(@RequestHeader("X-token") String token) {
        HashMap<String, Object> response = new HashMap<>();
        String name = Token.getUsernameFromToken(token);
        String workID = Token.getWorkIDFromToken(token);
        try {
            if (busService.getNumber() <= 0) {
                Logger.getGlobal().info("由缓存可知，已无库存");
                response.put("code", 100);
            }else {
                int num = busService.createOrder();
                Passenger passenger=new Passenger();
                passenger.setName(name);
                passenger.setWorkID(workID);
                producer.addToMq(passenger);
                Logger.getGlobal().info("购买成功，剩余库存为: [{" + (num-1)+"}]");
                response.put("message","提交成功");
                response.put("code", 20000);
            }
        } catch (Exception e) {
            Logger.getGlobal().info("失败");
            response.put("message","提交失败");
            response.put("code", 2000);
        }
        return response;
    }

    @CrossOrigin
    @GetMapping(value = "/bus/clear")
    @ResponseBody
    public Map clear() {
        HashMap<String, Object> response = new HashMap<>();
        busService.clear();
        System.out.println("清除缓存");
        Bus bus =new Bus();
        bus.setBid(1);
        bus.setNumber(50);
        bus.setSale(0);
        busRepository.save(bus);
        passengerService.truncateTable();
        //缓存数据可以在初始化的时候写好，这样就不会出现一开始是100的情况，不过这也无所谓
        response.put("code", 20000);
        return response;
    }

}
