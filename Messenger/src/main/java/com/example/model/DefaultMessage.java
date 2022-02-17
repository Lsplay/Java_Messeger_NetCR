package com.example.model;

import com.example.repository.GroupRepository;
import com.example.service.GroupService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="default_message")
public class DefaultMessage implements Message {

	
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long defaultMessageId;

	@Column(name = "message")
	@NotNull
	private String message;
//	private File image;

	@Column(name = "sender_id")
	@NotNull
	private Long sender;

	@Column(name = "time_of_send")
	@NotNull
	private Date timeOfSend = new Date();

	@Column(name = "is_checked")
	private boolean isChecked=false;

	@Column(name = "is_sended")
	private boolean isSended=false;

	@JsonIgnore
 	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "group_id", nullable = false)
	private Group group;

	public DefaultMessage() {

	}

	public DefaultMessage(String message, Group group, Long sender) {
		this.message = message;
		this.sender = sender;
		
		this.group=group;
	}

//	public DefaultMessage(File image, int idGroup, String sender) {
//		this.image = image;
//		this.idGroup = idGroup;
//		this.sender = sender;
//	}

	public String getMessage() {
		return message;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getSender() {
		return sender;
	}

	public void setSender(Long sender) {
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

	public Long getDefaultMessageId() {
		return defaultMessageId;
	}

	public void setDefaultMessageId(Long defaultMessageId) {
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
