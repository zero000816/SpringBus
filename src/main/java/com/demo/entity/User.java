package com.demo.entity;
import javax.persistence.Entity;
import javax.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    private String workId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    Set<RoleUser> roleUsers;

    public Set<RoleUser> getRoleUsers() {
        return roleUsers;
    }
}
