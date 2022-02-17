package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "groups")
public class Group {

	
	
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    public Long groupId;

    @Column(name="group_name")
    @NotNull
    private String name;
    
    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CryptedMessage> crMesseges=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DefaultMessage> dfMessages=new ArrayList<>();


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "group_users", 
        joinColumns = { @JoinColumn(name = "group_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "users_id") }
    )
    Set<User> users = new HashSet<>();

    public Long getId() {
        return groupId;
    }

    public void setId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addUser(User user){
        this.users.add(user);
    };

    public List<CryptedMessage> getCrMesseges() {
		return crMesseges;
	}

	public void setCrMesseges(List<CryptedMessage> crMesseges) {
		this.crMesseges = crMesseges;
	}
	
	public void addCrMessege(CryptedMessage cryptedMessage) {
		this.crMesseges.add(cryptedMessage);
	}

	public void addDefMessege(DefaultMessage defaultMessage) {
		this.dfMessages.add(defaultMessage);
	}
	public List<DefaultMessage> getDfMessages() {
		return dfMessages;
	}

	public void setDfMessages(List<DefaultMessage> dfMessages) {
		this.dfMessages = dfMessages;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId == group.groupId && Objects.equals(name, group.name) && Objects.equals(crMesseges, group.crMesseges) && Objects.equals(dfMessages, group.dfMessages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, name, crMesseges, dfMessages);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", name='" + name + '\'' +
                ", crMesseges=" + crMesseges +
                ", dfMessages=" + dfMessages +
                '}';
    }
    
    public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Group(Set<User> users,String name) {
    	this.users=users;
    	if(users.size()==2) {
    		Iterator<User> iter=users.iterator();
    		while(iter.hasNext()) {
    			this.name+=iter.next().getUserName();
    		}
    	}
    }
	public Group(String name) {
    	
    	this.name=name;
    }
	public Group() {
    	this.name="Null";
    }
}
