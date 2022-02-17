package com.example.service;

import com.example.model.CryptedMessage;
import com.example.model.Group;
import com.example.repository.CryptedMessageRepository;
import com.example.utilities.RSAUtill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Service
public class CryptedMessageService  {

    @Autowired
    CryptedMessageRepository cryptedMessageRepository;

    @Autowired
    private RSAUtill rsaUtill;


    public String cryptMessage(String message)throws InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
        return message = Base64.getEncoder().encodeToString(rsaUtill.encrypt(message, rsaUtill.getPublick()));
    }

    public String decryptMessage(String message){
        String dcrMessage=null;
        try {
            dcrMessage = RSAUtill.decrypt(message, rsaUtill.getPrivate());
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
                | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return dcrMessage;
    }


    public void create(CryptedMessage cryptedMessage) throws IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        if(cryptedMessage!=null){
        	Group gr=cryptedMessage.getGroup();
        	gr.addCrMessege(cryptedMessage);
            cryptedMessage.setMessage(cryptMessage(cryptedMessage.getMessage()));
            cryptedMessageRepository.save(cryptedMessage);
        }

    }

    public CryptedMessage read(Long id) {
        CryptedMessage cryptedMessage=cryptedMessageRepository.getById(id);
        cryptedMessage.setMessage(decryptMessage(cryptedMessage.getMessage()));
        return cryptedMessage;
    }

    public CryptedMessage updateMessage(CryptedMessage newMessage,Long id) throws IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        if(cryptedMessageRepository.findById(id)!=null) {
            CryptedMessage defaultMessage = cryptedMessageRepository.findById(id).orElseThrow();
            cryptedMessageRepository.deleteById(id);
            newMessage.setMessage(cryptMessage(newMessage.getMessage()));
            newMessage.setMessageId(id);
            cryptedMessageRepository.save(newMessage);
            return newMessage;
        }
        return null;
    }

    public boolean delete(Long id){
        if(cryptedMessageRepository.existsById(id)){
            cryptedMessageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CryptedMessage loadByMessage(String message){
        CryptedMessage cryptedMessage=cryptedMessageRepository.findCryptedMessageByMessage(message);
        cryptedMessage.setMessage(decryptMessage(cryptedMessage.getMessage()));
        return cryptedMessage;
    }

    public List<CryptedMessage> loadBySender(String sender){
        List<CryptedMessage> cryptedMessage=cryptedMessageRepository.findAllDCryptedMessagesBySender(sender);
        for(int i=0;i<cryptedMessage.size();i++)
        {
            cryptedMessage.get(i).setMessage(decryptMessage(cryptedMessage.get(i).getMessage()));
        }
        return cryptedMessage;
    }

    public List<CryptedMessage> loadByGroup(int groupId){
        List<CryptedMessage> cryptedMessage=cryptedMessageRepository.findAllCryptedMessagesByGroup(groupId);
        for(int i=0;i<cryptedMessage.size();i++)
        {
            cryptedMessage.get(i).setMessage(decryptMessage(cryptedMessage.get(i).getMessage()));
        }
        return cryptedMessage;
    }


}
