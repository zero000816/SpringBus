package com.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int rid;
    @Column
    String name;
    @Column
    int status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    Set<RoleUser> roleUsers;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    Set<RoleAccess> roleAccesses;


}
