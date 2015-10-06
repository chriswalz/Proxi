package com.joltimate.proxi;

import android.location.Location;

import java.util.ArrayList;

public class UserPost {
    private String message;
    private String userId;
    private int upvotes;
    private Location location;
    private ArrayList<String> group;
    //private ArrayList<UserPost> replies;

    private UserPost() {
    }

    public UserPost(String message, String user) {
        this.message = message;
        this.userId = user;
        this.upvotes = 0;
    }

    public String getMessage() {
        return message;
    }

}
