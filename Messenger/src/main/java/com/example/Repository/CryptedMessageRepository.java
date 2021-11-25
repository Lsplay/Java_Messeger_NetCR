package com.example.Repository;

import com.example.model.CryptedMessage;
import com.example.model.DefaultMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CryptedMessageRepository extends JpaRepository<CryptedMessage,Integer> {



    public CryptedMessage findCryptedMessageByMessage(String message);
    public List<CryptedMessage> findAllDCryptedMessagesBySender(String sender);
    public List<CryptedMessage> findAllCryptedMessagesByGroup(int groupId);


}
