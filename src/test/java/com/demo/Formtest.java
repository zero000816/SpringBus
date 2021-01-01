package com.demo;

import com.demo.entity.vo.ChooseForm;
import com.demo.mq.producer.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class Formtest {

    @Test
    public void test(){
        ChooseForm chooseForm=new ChooseForm();
        chooseForm.setBuildingID("1");
        chooseForm.setFirstStudent("test");
        List<String> studentsIDs=new ArrayList<>();
        studentsIDs.add("200121001");
        chooseForm.setStudentIDs(studentsIDs);
        Producer producer=new Producer();
        try {
            producer.addToMq(chooseForm);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}
