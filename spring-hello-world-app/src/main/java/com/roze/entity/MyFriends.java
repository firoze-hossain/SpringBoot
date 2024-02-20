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
        System.out.println("Doing some init stuff");
        friendList = new ArrayList<>();
        friendList.add("Millat");
        friendList.add("Rayhan");
        friendList.add("Imtiaze");
        friendList.add("Shajib");
    }

    public List<String> getAllFiendsList() {
        return friendList;
    }

    public int totalFriends() {
        return friendList.size();
    }

    public void addFriend(String friendName) {
        friendList.add(friendName);
    }
    @PreDestroy
    public void destroy(){
        System.out.println("Destroy is called");
        friendList.clear();
    }
}
