package com.example.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    public int groupId;

    @Column(name="grop_name")
    @NotNull
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<CryptedMessage> crMesseges=new ArrayList<>();

    @OneToMany(mappedBy = "group_id",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<DefaultMessage> dfMessages=new ArrayList<>();

    @OneToMany(mappedBy = "group_id",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<User> user=new ArrayList<>();


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
        this.user.add(user);
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
