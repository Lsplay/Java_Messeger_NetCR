package com.example.service;

import com.example.model.DefaultMessage;
import com.example.model.Group;
import com.example.model.User;
import com.example.repository.DefaultMessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMessageService  {

    @Autowired
    DefaultMessageRepository defaultMessageRepository;

    public void create(DefaultMessage defaultMessage){
        if(defaultMessage!=null){
        	Group gr=defaultMessage.getGroup();
        	gr.addDefMessege(defaultMessage);
            defaultMessageRepository.save(defaultMessage);
        }

    }

    public DefaultMessage read(Long id) {
        return defaultMessageRepository.getById(id);
    }

    public DefaultMessage updateMessage(DefaultMessage newMessage,Long id){
        if(defaultMessageRepository.findById(id)!=null) {
            DefaultMessage defaultMessage = defaultMessageRepository.findById(id).orElseThrow();
            defaultMessageRepository.deleteById(id);
            newMessage.setDefaultMessageId(id);
            defaultMessageRepository.save(newMessage);
            return newMessage;
        }
        return null;
    }

    public boolean delete(Long id){
        if(defaultMessageRepository.existsById(id)){
            defaultMessageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public DefaultMessage loadByMessage(String message){
        return defaultMessageRepository.findDefaultMessageByMessage(message);
    }

    public List<DefaultMessage> loadBySender(String message){
        return defaultMessageRepository.findAllDefaultMessagesBySender(message);
    }

    public List<DefaultMessage> loadByGroup(int groupId){
        return defaultMessageRepository.findAllDefaultMessagesByGroup(groupId);
    }

}
