package com.demo.repository;
import com.demo.entity.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser,Long>
{
    Set<RoleUser> findAllByUserId(int uid);

    void deleteByUserIdAndRoleId(int uid,int rid);
}
