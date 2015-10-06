package com.joltimate.proxi;

public class UserPost {
    private String message;
    private String userId;
    private int upvotes;
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
