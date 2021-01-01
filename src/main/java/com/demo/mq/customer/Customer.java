package com.demo.mq.customer;

import com.demo.entity.User;
import com.demo.entity.vo.ChooseForm;
import com.demo.service.impl.StudentServiceImpl;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.SynchronousQueue;

@Component
public class Customer {
    @Autowired
    StudentServiceImpl studentService;

    @RabbitListener(queuesToDeclare = @Queue("hello"))
    public void receive1(User message){
        System.out.println("hello message1 = " + message.getName());
    }
    
    @RabbitListener(queuesToDeclare = @Queue("choose"))
    public void receive2(String message){
        Gson gson = new Gson();
        ChooseForm form = gson.fromJson(message, ChooseForm.class);
        System.out.println(form.getFirstStudent());
        studentService.ChooseForm(form.getBuildingID(),form.getGender(),form.getFirstStudent(),form.getStudentIDs());
    }
}
