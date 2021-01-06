package com.demo.service;

import com.demo.entity.Role;
import com.demo.entity.RoleUser;
import com.demo.entity.User;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUser();
    User findByName(String name);
    User findByWorkID(String workID);
    Set<Role> getAllRoles(int uid);
    boolean updateRoles(String name, Set<RoleUser> newRoleUsers);
    User save(User user);
    void detele(User user);
}
