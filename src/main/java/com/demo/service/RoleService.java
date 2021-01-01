package com.demo.service;

import com.demo.entity.Role;

public interface RoleService {
    Role findByName(String name);
    Role findByRid(int rid);
}
