package com.demo.repository;

import com.demo.entity.Access;
import com.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRepository extends JpaRepository<Access,Long> {
    Access findByAid(int aid);
}
