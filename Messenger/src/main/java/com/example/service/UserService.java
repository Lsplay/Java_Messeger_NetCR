package com.example.service;


import com.example.repository.UserRepository;
import com.example.utilities.PasswordEncoder;
import com.example.model.Role;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUserName(username);
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
        return passwordEncoder.encode(password);
    }

    public User read(int id) {
        return userRepository.getById(id);
    }

    public User updateUser(User newUser,int id){
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

    public boolean delete(int id){
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

        user.setRoles(Collections.singleton(new Role(1,"ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return true;
    }
}
