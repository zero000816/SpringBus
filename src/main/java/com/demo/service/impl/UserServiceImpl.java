package com.demo.service.impl;

import com.demo.entity.Role;
import com.demo.entity.RoleUser;
import com.demo.entity.User;
import com.demo.repository.RoleRepository;
import com.demo.repository.RoleUserRepository;
import com.demo.repository.UserRepository;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleUserRepository roleUserRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
    }

    @Override
    public Set<Role> getAllRoles(int uid) {
        Set<RoleUser> roleUserSet = roleUserRepository.findAllByUserId(uid);
        Set<Role> roles = new HashSet<>();
        for (RoleUser roleUser: roleUserSet) {
            if (roleUser.getStatus() != 1) {
                Role role = roleRepository.findByRid(roleUser.getRoleId());
                if (role != null)
                    roles.add(role);
            }
        }
        return roles;
    }

    @Override
    public User findByName(String name){
        return userRepository.findByName(name);

    }

    @Override
    public User findByWorkID(String workID) {
        return userRepository.findByWorkID(workID);
    }

    @Override
    @Transactional
    public boolean updateRoles(String name, Set<RoleUser> newRoleUsers) {
        User user = findByName(name);
        Set<RoleUser> originRoles = user.getRoleUsers();
        for (RoleUser ru: originRoles){
            if (!newRoleUsers.contains(ru)){
                ru.setStatus(1);
                roleUserRepository.save(ru);
            }
        }
        for (RoleUser roleUser: newRoleUsers) {
            roleUserRepository.save(roleUser);
            user.getRoleUsers().add(roleUser);
        }
        userRepository.save(user);
        return true;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void detele(User user) {
        userRepository.delete(user);
    }

}
