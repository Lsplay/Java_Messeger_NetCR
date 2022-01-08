package com.example.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    public int groupId;

    @Column(name="group_name")
    @NotNull
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CryptedMessage> crMesseges=new ArrayList<>();

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DefaultMessage> dfMessages=new ArrayList<>();


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "group_users", 
        joinColumns = { @JoinColumn(name = "group_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "users_id") }
    )
    Set<User> users = new HashSet<>();

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
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
}
