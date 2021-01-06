package com.demo.mq.customer;

import com.demo.entity.User;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Customer {
/*    @Autowired
    StudentServiceImpl studentService;*/

    @RabbitListener(queuesToDeclare = @Queue("hello"))
    public void receive1(User message){
        System.out.println("hello message1 = " + message.getName());
    }
    
/*    @RabbitListener(queuesToDeclare = @Queue("choose"))
    public void receive2(String message){
        Gson gson = new Gson();
        ChooseForm form = gson.fromJson(message, ChooseForm.class);
        System.out.println(form.getFirstStudent());
        studentService.ChooseForm(form.getBuildingID(),form.getGender(),form.getFirstStudent(),form.getStudentIDs());
    }*/
}
