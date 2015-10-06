package com.joltimate.proxi;

import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.FirebaseException;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class FirebaseWrapper {
    private final String fireBaseURL = "https://proxi.firebaseio.com/";
    private Firebase firebaseRef;
    private BaseClutterActivity baseClutterActivity;
    private ListView listView;

    public FirebaseWrapper(ListView listView, BaseClutterActivity baseClutterActivity) {
        firebaseRef = new Firebase(fireBaseURL);
        setUpFirebase();
        this.listView = listView;
        this.baseClutterActivity = baseClutterActivity;
    }

    public void postMessageToFirebase(UserPost userPost) {
        firebaseRef.child("message").push().setValue(userPost);
        // firebaseRef.child("message").setValue(userTextPost);
    }

    private void setUpFirebase() {
        firebaseRef.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //parseDataChangeSnapShot(snapshot);
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
        firebaseRef.child("message").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                parseChildEvent(dataSnapshot, s);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    protected void parseChildEvent(DataSnapshot snapshot, String s) { // todo check if null, check if is indeed a string
        UserPost newUserPost;
        try {
            newUserPost = snapshot.getValue(UserPost.class);

        } catch (FirebaseException fe) {
            newUserPost = new UserPost("Unknown", "0"); //todo user different id?
        }
        System.out.println("Child event: " + newUserPost.toString());
        ArrayList<String> singleStringList = new ArrayList<>();
        singleStringList.add(newUserPost.getMessage());
        baseClutterActivity.changeList(listView, singleStringList);
    }
}
