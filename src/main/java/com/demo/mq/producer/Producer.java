package com.demo.mq.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;

/*    public void addToMq(ChooseForm chooseForm)throws JsonProcessingException {
        Gson gson= new Gson();
        System.out.println(gson.toJson(chooseForm));
        rabbitTemplate.convertAndSend("choose",gson.toJson(chooseForm));
        System.out.println("mq on");
    }*/
}
