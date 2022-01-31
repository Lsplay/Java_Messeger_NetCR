package com.example.repository;

import com.example.model.Group;
import com.example.model.User;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GroupRepository  extends JpaRepository<Group,Long> {

    public Group findGroupByName(String name);
	public Group getById(Long id);
	public List<Group> findByUsersIn(Set<User> users);

}
