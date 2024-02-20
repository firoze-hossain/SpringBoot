package com.roze.controller;

import com.roze.entity.MyFriends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendController {
    @Autowired
    private MyFriends myFriends;

    @GetMapping("/friends/list")
    public List<String> getAllfriendList() {
        return myFriends.getAllFriendList();
    }

    @GetMapping("/friends/count")
    public int totalFriends() {
        return myFriends.totalFriends();
    }

    @RequestMapping("/friends/add/{friendName}")
    public List<String> addFriend(@PathVariable("friendName") String friendName) {
        myFriends.addFriend(friendName);
        List<String> firendsList = myFriends.getAllFriendList();
        return firendsList;
    }

}
