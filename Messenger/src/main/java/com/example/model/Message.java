package com.example.model;

import java.util.Date;

public interface Message {

	public void setMessage(String string);
	public String getMessage();
//	public void setImage(File file);
//	public File getImage();
	public void setSender(int string);
	public int getSender();
	public void setTimeOfSend(Date date);
	public Date getTimeOfSend();
	public void messageIsChecked();
	public void messageIsSended();

	
}
