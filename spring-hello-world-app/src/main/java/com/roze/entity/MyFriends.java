package com.roze.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyFriends {
    private List<String> friendList;

    public List<String> getAllFiendsList() {
        friendList = new ArrayList<>();
        friendList.add("Millat");
        friendList.add("Rayhan");
        friendList.add("Imtiaze");
        friendList.add("Shajib");
        return friendList;
    }

    public int totalFriends() {
        friendList = new ArrayList<>();
        friendList.add("Millat");
        friendList.add("Rayhan");
        friendList.add("Imtiaze");
        friendList.add("Shajib");
        return friendList.size();
    }
}
