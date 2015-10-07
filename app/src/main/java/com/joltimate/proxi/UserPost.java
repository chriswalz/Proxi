package com.joltimate.proxi;

import android.location.Location;

import java.sql.Time;
import java.util.ArrayList;

public class UserPost {
    public int upvotes;
    public String firebaseKey;
    private String message;
    private int reports;
    private String topic;
    private Location location;
    private Time timeStamp;
    private ArrayList<String> replies;
    private User user;

    //private ArrayList<UserPost> replies;

    private UserPost() {
    }

    public UserPost(String message, User user) {
        this.message = message;
        this.user = user;
        this.upvotes = 0;

    }

    public String getMessage() {
        return message;
    }

}
