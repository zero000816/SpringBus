package com.test;


import com.demo.DemoApplication;
import com.demo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = DemoApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHello(){
        User user=new User();
        user.setName("test");
        rabbitTemplate.convertAndSend("hello","hello world");
        System.out.println("rabbit");
    }
}
