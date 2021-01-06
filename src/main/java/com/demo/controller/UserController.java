package com.demo.controller;
import com.demo.entity.Role;
import com.demo.entity.RoleUser;
import com.demo.entity.User;
import com.demo.entity.vo.UserAddVo;
import com.demo.entity.vo.UserVerifyVo;
import com.demo.entity.vo.UserVo;
import com.demo.repository.RoleUserRepository;
import com.demo.service.UserService;
import com.demo.service.impl.RoleServiceImpl;
import com.demo.service.impl.UserServiceImpl;
import com.demo.utils.MD5;
import com.demo.utils.Token;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleUserRepository roleUserRepository;

    @CrossOrigin
    @GetMapping(value = "/user/list")
    @ResponseBody
    public Map list() {
        HashMap<String, Object> response = new HashMap<>();
        Set<UserVo> responseData = new HashSet<>();
        for(User user:userService.getAllUser()) {
            UserVo userVo=new UserVo();
            userVo.setName(user.getName());
            userVo.setWorkID(user.getWorkID());
            List<String> roleList=new ArrayList<>();
            for(Role role:userService.getAllRoles(user.getUid())){
                roleList.add(role.getName());
            }
            userVo.setRole(roleList);
            responseData.add(userVo);
        }
        System.out.println("list");
        response.put("code", 20000);
        response.put("msg", "登录成功");
        response.put("data", responseData);
        return response;
    }

    @CrossOrigin
    @PostMapping(value = "/user/add")
    @ResponseBody
    public Map add(@RequestBody  UserAddVo userAddVo){
        System.out.println(userAddVo);
        HashMap<String,Object> response =new HashMap<>();
        User userAdmin=userService.findByWorkID(userAddVo.getAdminWorkID());
        if(userService.findByWorkID(userAddVo.getWorkID())!=null){
            response.put("code", 2002);
            response.put("message", "账号错误");
        }
        else if(MD5.encrypt(userAddVo.getAdminPassword()).equals(userAdmin.getPassword())){
            User user=new User();
            user.setName(userAddVo.getName());
            user.setPassword(MD5.encrypt(userAddVo.getPassword()));
            user.setWorkID(userAddVo.getWorkID());
            userService.save(user);
            User user1=userService.findByWorkID(user.getWorkID());
            for(String role:userAddVo.getRoles()){
                if(role.equals("student")){
                    RoleUser roleUserTemp= new RoleUser();
                    roleUserTemp.setUserId(user1.getUid());
                    roleUserTemp.setRoleId(3);
                    roleUserRepository.save(roleUserTemp);
                }
                if(role.equals("busAdmin")){
                    RoleUser roleUserTemp= new RoleUser();
                    roleUserTemp.setUserId(user1.getUid());
                    roleUserTemp.setRoleId(2);
                    roleUserRepository.save(roleUserTemp);
                }
                if(role.equals("superAdmin")){
                    RoleUser roleUserTemp= new RoleUser();
                    roleUserTemp.setUserId(user1.getUid());
                    roleUserTemp.setRoleId(1);
                    roleUserRepository.save(roleUserTemp);
                }

            }
            response.put("code", 20000);
            response.put("message", "密码修改成功");

        }else{
            response.put("code", 2002);
            response.put("message", "密码错误");
        }
        return response;
    }

    @CrossOrigin
    @PostMapping(value = "/user/modify")
    @ResponseBody
    public Map modify(@RequestBody UserVo userVo ){

        HashMap<String, Object> response = new HashMap<>();
        User userBack =userService.findByWorkID(userVo.getWorkID());
        System.out.println(userBack);
        System.out.println(userVo);
        if(userBack.getPassword().equals(MD5.encrypt(userVo.getOriginalPassword()))){
            userBack.setPassword(MD5.encrypt(userVo.getPassword()));
            userService.save(userBack);
            response.put("code", 20000);
            response.put("message", "密码修改成功");

        }else{
            response.put("code", 2002);
            response.put("message", "密码错误");
        }

        return response;
    }

    @CrossOrigin
    @PostMapping(value = "/user/unlock")
    @ResponseBody
    public Map unlock(@RequestBody UserVerifyVo user){
        HashMap<String, Object> response = new HashMap<>();
        User userAdmin =userService.findByWorkID(user.getAdminWorkID());
        User userBack =userService.findByWorkID(user.getWorkID());
        if(userAdmin.getPassword().equals(MD5.encrypt(user.getAdminPassword()))){
            userBack.setStatus(0);
            userService.save(userBack);
            response.put("code", 20000);
            response.put("message", "解锁成功");

        }else{
            response.put("code", 2002);
            response.put("message", "密码错误");
        }

        return response;
    }

    @CrossOrigin
    @PostMapping(value = "/user/deleteUser")
    @ResponseBody
    public Map delete(@RequestBody UserVerifyVo user){
        HashMap<String, Object> response = new HashMap<>();
        User userAdmin =userService.findByWorkID(user.getAdminWorkID());
        User userBack =userService.findByWorkID(user.getWorkID());
        if(userAdmin.getPassword().equals(MD5.encrypt(user.getAdminPassword()))){
            userService.detele(userBack);
            response.put("code", 20000);
            response.put("message", "删除成功");

        }else{
            response.put("code", 2002);
            response.put("message", "密码错误");
        }

        return response;
    }

}