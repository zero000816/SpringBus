package com.demo.entity;

import com.demo.entity.RoleAccess;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
public class Access {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int aid;
    @Column
    String name;
    @Column
    int status;
    @Column
    int level;
    @Column
    String module;
    @Column
    String identifier;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "access_id")
    Set<RoleAccess> roleAccesses;
}