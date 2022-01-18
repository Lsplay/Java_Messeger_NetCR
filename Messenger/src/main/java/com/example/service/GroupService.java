package com.example.service;

import com.example.model.Group;
import com.example.model.User;
import com.example.repository.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

	@Autowired
	GroupRepository groupRepository;

	public Group loadUserByUsername(String name) throws UsernameNotFoundException {
		return groupRepository.findGroupByName(name);
	}

	public void create(Group group) {

		if (group != null) {
			groupRepository.save(group);
		}

	}

	public boolean saveGroup(Group group) {
		Group groupFromDb = groupRepository.findGroupByName(group.getName());

		if (groupFromDb != null) {
			return false;
		}

		groupRepository.save(group);

		return true;
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

	public List<Group> readAll() {
		return groupRepository.findAll();
	}
}
