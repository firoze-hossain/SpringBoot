package com.roze.controller;

import com.roze.entity.MyFriends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FriendController {
    @Autowired
    public MyFriends myFriends;

    @RequestMapping("/friends/list")
    public List<String> getAllFriendsName() {
        return myFriends.getAllFiendsList();
    }

    @RequestMapping("/friends/count")
    public int getFriendsCount() {
        return myFriends.totalFriends();
    }

    @RequestMapping("/friends/add/{friendName}")
    public List<String> addFriend(@PathVariable("friendName") String friendName) {
        myFriends.addFriend(friendName);
        List<String> allFiendsList = myFriends.getAllFiendsList();
        return allFiendsList;
    }
}
