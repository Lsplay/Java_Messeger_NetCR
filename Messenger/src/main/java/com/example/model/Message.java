package com.example.model;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public interface Message {

	public void setMessage(String string);
	public String getMessage();
//	public void setImage(File file);
//	public File getImage();
	public void setSender(String string);
	public String getSender();
	public void setTimeOfSend(Date date);
	public Date getTimeOfSend();
	public void messageIsChecked();
	public void messageIsSended();

	
}
