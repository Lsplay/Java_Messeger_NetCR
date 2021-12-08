package com.example.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="default_message")
public class DefaultMessage implements Message {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private int defaultMessageId;

	@Column(name = "message")
	@NotNull
	private String message;
//	private File image;

	@Column(name = "sender_id")
	@NotNull
	private int sender;

	@Column(name = "time_of_send")
	@NotNull
	private Date timeOfSend = new Date();

	@Column(name = "is_checked")
	private boolean isChecked=false;

	@Column(name = "is_sended")
	private boolean isSended=false;


 	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "group_id", nullable = false)
	private Group group;

	public DefaultMessage() {

	}

	public DefaultMessage(String message, int idGroup, int sender) {
		this.message = message;
		this.sender = sender;
	}

//	public DefaultMessage(File image, int idGroup, String sender) {
//		this.image = image;
//		this.idGroup = idGroup;
//		this.sender = sender;
//	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
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

	public int getDefaultMessageId() {
		return defaultMessageId;
	}

	public void setDefaultMessageId(int defaultMessageId) {
		this.defaultMessageId = defaultMessageId;
	}

	@Override
	public String toString() {
		return "Message{" +
				"MessageId=" + defaultMessageId +
				", message='" + message + '\'' +
				", sender='" + sender + '\'' +
				", timeOfSend=" + timeOfSend +
				", isChecked=" + isChecked +
				", isSended=" + isSended +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DefaultMessage that = (DefaultMessage) o;
		return defaultMessageId == that.defaultMessageId && sender == that.sender && isChecked == that.isChecked && isSended == that.isSended && message.equals(that.message) && timeOfSend.equals(that.timeOfSend) && group.equals(that.group);
	}

	@Override
	public int hashCode() {
		return Objects.hash(defaultMessageId, message, sender, timeOfSend, isChecked, isSended);
	}
}
