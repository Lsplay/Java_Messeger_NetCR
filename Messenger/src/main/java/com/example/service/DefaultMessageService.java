package com.example.service;

import com.example.repository.DefaultMessageRepository;
import com.example.model.DefaultMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultMessageService  {

    @Autowired
    DefaultMessageRepository defaultMessageRepository;

    public void create(DefaultMessage defaultMessage){
        if(defaultMessage!=null){
            defaultMessageRepository.save(defaultMessage);
        }

    }

    public DefaultMessage read(int id) {
        return defaultMessageRepository.getById(id);
    }

    public DefaultMessage updateMessage(DefaultMessage newMessage,int id){
        if(defaultMessageRepository.findById(id)!=null) {
            DefaultMessage defaultMessage = defaultMessageRepository.findById(id).orElseThrow();
            defaultMessageRepository.deleteById(id);
            newMessage.setDefaultMessageId(id);
            defaultMessageRepository.save(newMessage);
            return newMessage;
        }
        return null;
    }

    public boolean delete(int id){
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
