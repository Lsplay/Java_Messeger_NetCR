package com.example.model;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.persistence.*;

import com.example.model.Message;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.Utilities.RSAUtill;
@Entity
@Table(name="crypted_message")
public class CryptedMessage implements Message {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int messageId;

	@Column(name="sender")
	@NotNull
	private String sender;

	@Column(name="message")
	@NotNull
	private String message;

	@Column(name="is_checked")
	private boolean isChecked = false;

	@Column(name="is_sended")
	private boolean isSended = false;

	@Column(name="time_of_send")
	@NotNull
	private Date timeOfSend=new Date();


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", nullable = false)
	private Group group;

	public CryptedMessage() {
		
	}
	
	public CryptedMessage(String message,int idGroup, String sender) {
		this.setMessage(message);
		this.sender=sender;
	}
	
//	public CryptedMessage(File image,int idGroup, String sender) {
//		this.image=image;
//		this.idGroup=idGroup;
//		this.sender=sender;
//	}

	public String getMessage() { return message; }

	public void setMessage(String message) { this.message=message; }
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
	public void messageIsChecked() {
		this.isChecked=true;
	}
	public void messageIsSended() {
		this.isSended=true;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CryptedMessage that = (CryptedMessage) o;
		return messageId == that.messageId && isChecked == that.isChecked && isSended == that.isSended && sender.equals(that.sender) && message.equals(that.message) && timeOfSend.equals(that.timeOfSend);
	}

	@Override
	public int hashCode() {
		return Objects.hash(messageId, sender, message, isChecked, isSended, timeOfSend);
	}

	@Override
	public String toString() {
		return "CryptedMessage{" +
				"messageId=" + messageId +
				", sender='" + sender + '\'' +
				", message='" + message + '\'' +
				", isChecked=" + isChecked +
				", isSended=" + isSended +
				", timeOfSend=" + timeOfSend +
				'}';
	}
}
