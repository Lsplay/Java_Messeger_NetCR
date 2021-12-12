package com.example.repository;

import com.example.model.CryptedMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CryptedMessageRepository extends JpaRepository<CryptedMessage,Integer> {



    public CryptedMessage findCryptedMessageByMessage(String message);
    public List<CryptedMessage> findAllDCryptedMessagesBySender(String sender);
    public List<CryptedMessage> findAllCryptedMessagesByGroup(int groupId);


}
