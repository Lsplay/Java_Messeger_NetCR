package com.example.Classes;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Utilities.RSAUtill;

public class CryptedMessage implements Message {
	private boolean isChecked = false;
	private boolean isSended = false;
	private String message;
	private int idGroup;
	private File image;
	private String sender;
	private Date timeOfSend=new Date();
	@Autowired
	private RSAUtill rsaUtill;
	
	

	public CryptedMessage() {
		
	}
	
	public CryptedMessage(String message,int idGroup, String sender) {
		this.setMessage(message);
		this.idGroup=idGroup;
		this.sender=sender;
	}
	
	public CryptedMessage(File image,int idGroup, String sender) {
		this.image=image;
		this.idGroup=idGroup;
		this.sender=sender;
	}
	
	private String criptMessage(String message) throws InvalidKeyException, BadPaddingException,
			IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {

		this.message = Base64.getEncoder().encodeToString(rsaUtill.encrypt(message, rsaUtill.getPublick()));
		System.out.println(this.message);
		return this.message;

	}

	public String getMessage() {
		String decryptedMessage = null;
		try {
			decryptedMessage = RSAUtill.decrypt(message, rsaUtill.getPrivate());
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
				| NoSuchPaddingException e) {

			e.printStackTrace();
		}

		return decryptedMessage;
	}

	public void setMessage(String message) {
		try {
			this.message = criptMessage(message);
		} catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException
				| NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public int getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(int idGroup) {
		this.idGroup = idGroup;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getTimeOfSend() {
		return timeOfSend;
	}

	public void setTimeOfSend(Date timeOfSend) {
		this.timeOfSend = timeOfSend;
	}

	public void save() {
		/// Отправляем сообщение в бд(Если это фото не отапрвляем message и наоборот)
	}

	public void messageIsChecked() {
		this.isChecked=true;
	}
	public void messageIsSended() {
		this.isSended=true;
	}

}
