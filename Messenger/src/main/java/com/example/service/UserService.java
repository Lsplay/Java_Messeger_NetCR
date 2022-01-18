package com.example.service;



import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.utilities.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User loadUserByUsername(String username)  {
       
       if(userRepository.findUserByUserName(username)!=null) {
    	   return userRepository.findUserByUserName(username);
       }
    	throw new UsernameNotFoundException("User not exsist with name:"+ username);
    }
    
    public User loadUserById(Long id)  {
        
        if(userRepository.findById(id)!=null) {
     	   return userRepository.getById(id);
        }
     	throw new UsernameNotFoundException("User not exsist with id:"+ id);
     }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email);
    }

    public void create(User userModel){
        if(userModel!=null){
            userModel.setPassword( encodePassword(userModel.getPassword()));
            userRepository.save(userModel);
        }

    }

    public String encodePassword(String password) {
        return passwordEncoder.encoder(password);
    }

    public User read(Long id) {
        return userRepository.getById(id);
    }

    public User updateUser(User newUser,Long id){
        if(userRepository.findById(id)!=null) {
            User user = userRepository.findById(id).orElseThrow();
            userRepository.deleteById(id);
            newUser.setId(id);
            newUser.setPassword(encodePassword(newUser.getPassword()));
            userRepository.save(newUser);
            return newUser;
        }
        return null;
    }

    public boolean delete(Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<User> readAll(){
        return  userRepository.findAll();
    }


    public boolean saveUser(User user) {
        User userFromDb = (User) userRepository.findUserByUserName(user.getUserName());

        if(userFromDb!=null){
            return false;
        }
        user.setPassword(passwordEncoder.encoder(user.getPassword()));
       
        user.setActive(true);
        userRepository.save(user);
        

        return true;
    }
}
