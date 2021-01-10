package com.demo.mq.producer;

import com.demo.entity.Passenger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void addToMq(Passenger passenger)throws JsonProcessingException {
        Gson gson= new Gson();
        rabbitTemplate.convertAndSend("passenger",gson.toJson(passenger));
    }
}
