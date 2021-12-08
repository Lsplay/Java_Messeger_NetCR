package com.example.Classes;

import java.io.File;
import java.util.Date;

public class DefaultMessage implements Message {

	private String message;
	private int idGroup;
	private File image;
	private String sender;
	private Date timeOfSend = new Date();
	private boolean isChecked=false;
	private boolean isSended=false;
	
	public DefaultMessage() {

	}

	public DefaultMessage(String message, int idGroup, String sender) {
		this.message = message;
		this.idGroup = idGroup;
		this.sender = sender;
	}

	public DefaultMessage(File image, int idGroup, String sender) {
		this.image = image;
		this.idGroup = idGroup;
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
