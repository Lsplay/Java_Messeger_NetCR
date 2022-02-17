package com.example.service;

import com.example.model.Group;
import com.example.model.User;
import com.example.repository.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

@Service
@Transactional
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;

	public Group loadUserByUsername(String name) throws UsernameNotFoundException {
		return groupRepository.findGroupByName(name);
	}
	
	public void create(Group group) {

		if (group != null) {
			
			groupRepository.save(group);
		}

	}

	
	public Group read(Long id) {
		return groupRepository.getById(id);
	}

	public Group updateGroup(Group newGroup, Long id) {
		if (groupRepository.findById(id) != null) {
			Group group = groupRepository.findById(id).orElseThrow();
			groupRepository.deleteById(id);
			newGroup.setId(id);
			groupRepository.save(newGroup);
			return newGroup;
		}
		return null;
	}

	public boolean delete(Long id) {
		if (groupRepository.existsById(id)) {
			groupRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public List<Group> findByUser(User user){
		Set<User> users=new HashSet<User>();
		users.add(user);
		
		return groupRepository.findByUsersIn(users);
		
	}
	
	public List<Group> readAll() {
		return groupRepository.findAll();
	}
}
