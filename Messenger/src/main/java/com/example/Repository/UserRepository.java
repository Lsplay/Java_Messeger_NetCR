package com.example.Repository;

import com.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {

    public UserDetails findUserByUserName(String username);
    public UserDetails findUserByEmail(String email);

}
