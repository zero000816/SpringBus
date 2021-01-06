package com.demo.controller;

import com.demo.entity.Passenger;
import com.demo.entity.User;
import com.demo.entity.vo.BusVo;
import com.demo.service.impl.BusServiceImpl;
import com.demo.service.impl.UserServiceImpl;
import com.demo.utils.MD5;
import com.demo.utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class BusController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    BusServiceImpl busService;

    @CrossOrigin
    @PostMapping(value = "/bus/submit")
    @ResponseBody
    public Map submit(@RequestBody BusVo busVo){
        System.out.println(busVo);
        System.out.println(MD5.encrypt(busVo.getPassword()));
        HashMap<String, Object> response = new HashMap<>();
        User userBack=userService.findByWorkID(busVo.getWorkID());
        if (userBack == null) {
            response.put("code", 2000);
            response.put("message", "失败");
        } else if (userBack.getPassword().equals(MD5.encrypt(busVo.getPassword()))) {
            System.out.println("BUS");
            busService.setNumber(busVo.getNumber());
            response.put("code", 20000);
            response.put("message", "登录成功");
            System.out.println("in");
        }else{
            response.put("message", "登录失败");
        }
        return response;
    }

    @CrossOrigin
    @GetMapping(value = "/bus/list")
    @ResponseBody
    public Map ListPassenger(){
        HashMap<String, Object> response = new HashMap<>();
        Set<Passenger> passengerSet= new HashSet<>();
        for(Passenger passenger:busService.getAllPassenger()){
            Passenger passengerTemp=new Passenger();
            passengerTemp.setName(passenger.getName());
            passengerTemp.setWorkID(passenger.getWorkID());
            passengerSet.add(passengerTemp);
        }
        response.put("data",passengerSet);
        response.put("code", 20000);
        return response;
    }
}
