package com.example.Repository;

import com.example.model.DefaultMessage;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface DefaultMessageRepository extends JpaRepository<DefaultMessage,Integer> {

    public DefaultMessage findDefaultMessageByMessage(String message);
    public List<DefaultMessage> findAllDefaultMessagesBySender(String sender);
    public List<DefaultMessage> findAllDefaultMessagesByGroup(int groupId);


}
