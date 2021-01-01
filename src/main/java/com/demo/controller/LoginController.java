package com.demo.controller;

import com.demo.entity.Role;
import com.demo.entity.User;
import com.demo.service.UserService;
import com.demo.service.impl.UserServiceImpl;
import com.demo.utils.Token;
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
    @PostMapping(value = "/vue/user/login")
    @ResponseBody
    public Map login(@RequestBody User user) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        System.out.println(user.getName());
        if (userService.findByName(user.getName()).getPassword().equals(user.getPassword())) {
            String token=Token.getToken(user);
            responseData.put("token", token );
            response.put("code", 20000);
            response.put("msg", "登录成功");
            response.put("data", responseData);
            System.out.println("in");
            System.out.println(Token.getUsernameFromToken(token));
        }
        else{
            response.put("code", 2000);
            response.put("msg", "登录失败");
            response.put("data", responseData);
        }
        return response;
    }

    @CrossOrigin
    @GetMapping(value = "/vue/user/info")
    @ResponseBody
    public Map info(@RequestHeader("X-token") String token) {
        HashMap<String, Object> responseInfo = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        String name=Token.getUsernameFromToken(token);
        User user=userService.findByName(name);
        Set<String> set=new HashSet<>();
        Set<Role> roleSet =userService.getAllRoles(user.getUid());
        for(Role role:roleSet){
            set.add(role.getName());
        }
        responseData.put("roles",set);
        responseData.put("name",name);
        responseData.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        responseInfo.put("code",20000);
        responseInfo.put("msg","登录成功");
        responseInfo.put("data",responseData);
        System.out.println("info"+name);
        return responseInfo;
    }

    @CrossOrigin
    @PostMapping(value = "/vue/user/logout")
    @ResponseBody
    public Map logout (){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        response.put("code",20000);
        return response;
    }

    @CrossOrigin
    @PostMapping(value = "/vue/user/changePassword")
    @ResponseBody
    public Map changePassword (){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> responseData = new HashMap<>();
        responseData.put("token", 1);
        response.put("code", 20000);
        response.put("msg", "登录成功");
        response.put("data", responseData);
        System.out.println("change");
        return response;
    }



}