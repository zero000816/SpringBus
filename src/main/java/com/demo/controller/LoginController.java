package com.demo.controller;

import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import com.demo.service.UserService;
import com.demo.service.impl.UserServiceImpl;
import com.demo.utils.MD5;
import com.demo.utils.Token;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSetMetaData;
import java.util.*;

@Controller
public class LoginController {
    @Autowired
    private UserServiceImpl userService;

    @CrossOrigin
    @PostMapping(value = "/user/login")
    @ResponseBody
    public Map login(@RequestBody User user) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        User userBack = userService.findByWorkID(user.getWorkID());
        if (userBack == null) {
            response.put("code", 2000);
            response.put("message", "登录失败");
            response.put("data", responseData);
        } else if(userBack.getStatus()>=3){
            response.put("code", 2001);
            response.put("message", "错误尝试过多，账号已锁定，请联系管理员");
            response.put("data", responseData);
        } else if (userBack.getPassword().equals(MD5.encrypt(user.getPassword()))) {
            userBack.setStatus(0);
            userService.save(userBack);
            String token = Token.getToken(userBack);
            responseData.put("token", token);
            response.put("code", 20000);
            response.put("message", "登录成功");
            response.put("data", responseData);
            System.out.println("in");
        }else{
            userBack.setStatus(userBack.getStatus()+1);
            userService.save(userBack);
            System.out.println(userBack.getStatus());
            response.put("message", "登录失败");
            response.put("data", responseData);
        }
            return response;
    }

    @CrossOrigin
    @GetMapping(value = "/user/info")
    @ResponseBody
    public Map info(@RequestHeader("X-token") String token) {
        HashMap<String, Object> responseInfo = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        System.out.println("info");
        String name = Token.getUsernameFromToken(token);
        String workID = Token.getWorkIDFromToken(token);
        System.out.println("name"+name);
        User user = userService.findByWorkID(workID);
        Set<String> set = new HashSet<>();
        Set<Role> roleSet = userService.getAllRoles(user.getUid());
        for (Role role : roleSet) {
            set.add(role.getName());
        }
        responseData.put("roles", set);
        responseData.put("name", name);
        responseData.put("workID", workID);
        responseData.put("avatar", "https://dss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2691088385,3939984500&fm=26&gp=0.jpg");
        responseInfo.put("code", 20000);
        responseInfo.put("msg", "登录成功");
        responseInfo.put("data", responseData);
        System.out.println("info" + name);
        return responseInfo;
    }

    @CrossOrigin
    @PostMapping(value = "/user/logout")
    @ResponseBody
    public Map logout() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("code", 20000);
        return response;
    }



}