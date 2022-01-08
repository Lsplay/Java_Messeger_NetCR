package com.example.service;

import com.example.model.Group;
import com.example.model.User;
import com.example.repository.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public Group loadUserByUsername(String name) throws UsernameNotFoundException {
        return groupRepository.findGroupByName(name);
    }

    public void create(Group group){

        if(group!=null){

            groupRepository.save(group);
        }

    }


    public Group read(int id) {
        return groupRepository.getById(id);
    }

    public Group updateGroup(Group newGroup,int id){
        if(groupRepository.findById(id)!=null) {
            Group group = groupRepository.findById(id).orElseThrow();
            groupRepository.deleteById(id);
            newGroup.setGroupId(id);
            groupRepository.save(newGroup);
            return newGroup;
        }
        return null;
    }

    public boolean delete(int id){
        if(groupRepository.existsById(id)){
            groupRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Group> readAll(){
        return  groupRepository.findAll();
    }
}
