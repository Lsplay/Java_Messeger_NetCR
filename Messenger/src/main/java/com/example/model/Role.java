package com.example.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;


public enum Role  /*implements GrantedAuthority*/ {
	USER;
	
//    @Id
//    private int id;
//    private String name;
//
//    @Transient
//
//    @ManyToMany(cascade=CascadeType.ALL)
//    @JoinTable(
//            name="role_users",
//            joinColumns = {@JoinColumn(name="role_id")},
//            inverseJoinColumns = {@JoinColumn(name="users_id")}
//    )
//    private Set<User> users;
//    public Role() {
//    }
//
//    public Role(int id) {
//        this.id = id;
//    }
//
//    public Role(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
//
//    @Override
//    public String getAuthority() {
//        return getName();
//    }
}