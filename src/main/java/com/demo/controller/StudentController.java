package com.demo.controller;


import com.demo.entity.Role;
import com.demo.entity.Student;
import com.demo.entity.User;
import com.demo.entity.vo.ChooseForm;
import com.demo.entity.vo.StudentVo;
import com.demo.entity.vo.UserVo;
import com.demo.mq.producer.Producer;
import com.demo.service.impl.ChooseLogServiceImpl;
import com.demo.service.impl.StudentServiceImpl;
import com.demo.utils.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller

public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private Producer producer;

    @Autowired
    private ChooseLogServiceImpl chooseLogService;

    @CrossOrigin
    @PostMapping(value = "/vue/student/login")
    @ResponseBody
    public Map login(@RequestBody Student student) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        System.out.println(student.getStudentID());
        if (studentService.findByStudentID(student.getStudentID()).getVerifyCode().equals(student.getVerifyCode())) {
            String token= Token.getToken(student);
            responseData.put("token", token );
            response.put("code", 20000);
            response.put("msg", "登录成功");
            response.put("data", responseData);
            System.out.println("in");
        }
        else{
            response.put("code", 2000);
            response.put("msg", "登录失败");
            response.put("data", responseData);
        }
        return response;
    }

    @CrossOrigin
    @GetMapping(value = "/vue/student/info")
    @ResponseBody
    public Map info(@RequestHeader("X-token") String token) {
        HashMap<String, Object> responseInfo = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        String studentID=Token.getStudentIDFromToken(token);
        Student student=studentService.findByStudentID(studentID);
        responseData.put("name",student.getName());
        responseData.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        responseData.put("dormName",student.getDormName());
        responseData.put("studentID",student.getStudentID());
        responseData.put("verifyCode",student.getVerifyCode());
        responseInfo.put("code",20000);
        responseInfo.put("msg","登录成功");
        responseInfo.put("data",responseData);
        return responseInfo;
    }

    @CrossOrigin
    @PostMapping(value = "vue/student/studentList")
    @ResponseBody
    public Map list(){
        HashMap<String, Object> response = new HashMap<>();
        Set<StudentVo> responseData =new HashSet<>();
        for (Student student:studentService.getAllStudents()){
            StudentVo studentVo=new StudentVo();
            studentVo.setName(student.getName());
            studentVo.setStudentID(student.getStudentID());
            studentVo.setGender(student.getGender());
            studentVo.setDormName(student.getDormName());
            studentVo.setVerifyCode(student.getVerifyCode());
            responseData.add(studentVo);
        }
        System.out.println("studentList");
        response.put("code", 20000);
        response.put("msg", "登录成功");
        response.put("data", responseData);
        return response;
    }

    @CrossOrigin
    @PostMapping(value = "vue/student/submit")
    @ResponseBody
    public Map choose(
            @RequestBody ChooseForm chooseForm
            ){
        System.out.println("submit");
        System.out.println(chooseForm);
        HashMap<String, Object> response = new HashMap<>();
        try {
            producer.addToMq(chooseForm);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("az");
        response.put("code", 20000);
        response.put("msg", "传输成功");
        return response;
    }

    @CrossOrigin
    @GetMapping(value = "vue/student/getStudent")
    @ResponseBody
    public Map getStudent(String studentID){
        System.out.println("information");
        System.out.println(studentID);
        HashMap<String, Object> response = new HashMap<>();
        Student student=studentService.findByStudentID(studentID);
        response.put("code", 20000);
        response.put("msg", "传输成功");
        Map<String, Object> map = new HashMap<>();
        map.put("student", student);
       response.put("data",map);
        return response;
    }

    @CrossOrigin
    @GetMapping(value = "vue/student/log")
    @ResponseBody
    public Map log(String studentID){
        System.out.println("log");
        HashMap<String, Object> response = new HashMap<>();
        String flag=chooseLogService.findByStudentID(studentID).getInformation();
        response.put("code", 20000);
        response.put("msg", "传输成功");
        Map<String, Object> map = new HashMap<>();
        map.put("flag", flag);
        response.put("data",map);
        return response;
    }
}
