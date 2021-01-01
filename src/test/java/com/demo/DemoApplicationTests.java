package com.demo;

import com.demo.entity.Dorm;
import com.demo.repository.DormRepository;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan("com.dorm")
@MapperScan("com.dorm")
class DemoApplicationTests {

    @Autowired
    DormRepository dormRepository ;
    @Test
    void contextLoads() {
        Dorm dorm =new Dorm();
        for (int i=1;i<21;i++){
            String building=Integer.toString((int)(2*Math.random()+1));
            dorm.setBuilding(building);
            dorm.setTotalBed(4);
            dorm.setEmptyBed(4);
            int x=(int)(9*Math.random()+1);
            dorm.setGender((int)(2*Math.random()));
            dorm.setFloor((int)(Math.random()*5+1));
            dorm.setDormName(dorm.getBuilding()+dorm.getFloor()+x);
            dormRepository.save(dorm);
        }

    }

}
