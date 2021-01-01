package com.demo.service.impl;

import com.demo.entity.Role;
import com.demo.repository.RoleRepository;
import com.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Role findByRid(int rid){
        return roleRepository.findByRid(rid);
    }
}
