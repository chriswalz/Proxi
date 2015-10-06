package com.joltimate.proxi;

/**
 * Created by chris on 10/6/15.
 */
public class User {
    public final static User AnonUser = new User("0000", 0, 0);
    private String id;
    private int karmaScore;
    private int numberFollowers;

    private User() {
    }

    public User(String id, int karmaScore, int numberFollowers) {

    }

}
