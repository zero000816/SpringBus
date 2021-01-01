package com.demo.repository;

import com.demo.entity.RoleAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleAccessRepository extends JpaRepository<RoleAccess,Long> {
    Set<RoleAccess> findAllByRoleId(int role_id);
    void deleteByRoleIdAndAccessId(int rid,int aid);
}
