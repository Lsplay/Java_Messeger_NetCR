//package com.example.model;
//
//import org.springframework.security.core.GrantedAuthority;
//
//import javax.persistence.*;
//
//import java.util.Collection;
//import java.util.Set;
//
//@Embeddable
//public class Role implements GrantedAuthority{
//
//	
//	private String name;
//	
//	public Role() {
//		
//	}
//	
//    public Role(String name) {
//        
//        this.name = name;
//    }
//
//   
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public String getAuthority() {
//        return getName();
//    }
//	
//}