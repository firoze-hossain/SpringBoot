package com.roze.entity;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyFriends {
    private List<String> friendList;

    @PostConstruct
    public void init() {
        System.out.println("Init invoked");
        friendList = new ArrayList<>();
        friendList.add("Millat");
        friendList.add("Shajib");
        friendList.add("Rayhan");
        friendList.add("Imtiaze");
    }

    public List<String> getAllFriendList() {
        return friendList;
    }

    public int totalFriends() {
        return friendList.size();
    }

    public void addFriend(String friendName) {
        friendList.add(friendName);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroy invoked");
        friendList.clear();
    }

}
