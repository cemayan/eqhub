package org.dark.eqhub.friendsservice.domain.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node("User")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    @Relationship(type = "FOLLOWS", direction = Relationship.Direction.OUTGOING)
    private List<User> followList;

    @Relationship(type = "FOLLOWERS", direction = Relationship.Direction.OUTGOING)
    private List<User> followerList;


    public User() {

    }

    public User(String userName) {
        this.userName = userName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<User> getFollowList() {
        return followList;
    }

    public void setFollowList(List<User> followList) {
        this.followList = followList;
    }

    public List<User> getFollowerList() {
        return followerList;
    }

    public void setFollowerList(List<User> followerList) {
        this.followerList = followerList;
    }

    public void follow(User u) {
        this.followList.add(u);
        u.followerList.add(this);
    }

    public void unfollow(User u) {
        this.followList.add(u);
        u.followerList.add(this);
    }

}
