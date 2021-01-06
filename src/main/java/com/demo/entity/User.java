package com.demo.entity;
import javax.persistence.Entity;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

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

    @Column(unique = true)
    private String workID;
    @Column
    private int status =0;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    Set<RoleUser> roleUsers;

    public Set<RoleUser> getRoleUsers() {
        return roleUsers;
    }
}
