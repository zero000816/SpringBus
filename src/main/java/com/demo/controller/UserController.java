package com.demo.controller;
import com.demo.entity.Role;
import com.demo.entity.RoleUser;
import com.demo.entity.User;
import com.demo.entity.vo.UserVo;
import com.demo.service.UserService;
import com.demo.service.impl.RoleServiceImpl;
import com.demo.service.impl.UserServiceImpl;
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
    private RoleServiceImpl roleService;

    @CrossOrigin
    @PostMapping(value = "/vue/user/list")
    @ResponseBody
    public Map list() {
        HashMap<String, Object> response = new HashMap<>();
        Set<UserVo> responseData = new HashSet<>();
        for(User user:userService.getAllUser()) {
            UserVo userVo=new UserVo();
            userVo.setName(user.getName());
            userVo.setWorkId(user.getWorkId());
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
    @PostMapping(value = "/vue/user/modify")
    @ResponseBody
    public Map modify(@RequestBody UserVo userVo ){
        // 这里的逻辑应该接收工号和修改的权限，然后再数据库里根据工号找到这个user并把role更改,合理的逻辑是工号找uid，直接改rid）

        HashMap<String, Object> response = new HashMap<>();
        List<String> roleStringSet = userVo.getRole();
        User user =userService.findByWorkId(userVo.getWorkId());
        Set<RoleUser> roleUsers = user.getRoleUsers();
        Set<RoleUser> newRoleUsers = new HashSet<>();
        for (RoleUser ru: roleUsers){
            if (ru.getStatus() == 0){
                String ru_name = roleService.findByRid(ru.getRoleId()).getName();
                if (roleStringSet.contains(ru_name)){
                    newRoleUsers.add(ru);
                    roleStringSet.remove(ru_name);
                }
            }
        }
        if (roleStringSet.size()!=0){
            for (String ru: roleStringSet){
                RoleUser roleUser = new RoleUser();
                roleUser.setUserId(user.getUid());
                roleUser.setRoleId(roleService.findByName(ru).getRid());
                newRoleUsers.add(roleUser);
            }
        }
        System.out.println(newRoleUsers.size());
        userService.updateRoles(user.getName(), newRoleUsers);
        System.out.println("ok");
        response.put("code", 20000);
        response.put("msg", "登录成功");
        return response;
    }


}