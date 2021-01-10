package com.demo.mq.customer;

import com.demo.entity.Passenger;
import com.demo.entity.User;
import com.demo.repository.BusRepository;
import com.demo.service.PassengerService;
import com.demo.service.impl.PassengerServiceImpl;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Customer {
    @Autowired
    PassengerServiceImpl passengerService;

    @RabbitListener(queuesToDeclare = @Queue("hello"))
    public void receive1(User message){
        System.out.println("hello message1 = " + message.getName());
    }
    
    @RabbitListener(queuesToDeclare = @Queue("passenger"))
    public void receive2(String message){
        Gson gson = new Gson();
        Passenger passenger = gson.fromJson(message, Passenger.class);
        System.out.println(passenger);
        System.out.println("好像可以接收到信息？");
        try{
        passengerService.save(passenger);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
