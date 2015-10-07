package com.joltimate.proxi;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.FirebaseException;
import com.firebase.client.ValueEventListener;

public class FirebaseWrapper {
    private final String fireBaseURL = "https://proxi.firebaseio.com/";
    private final String firebaseChildPost = "post";

    private Firebase firebaseRef;
    private BaseClutterActivity baseClutterActivity;
    private RecyclerView recyclerView;

    public FirebaseWrapper(RecyclerView recyclerView, BaseClutterActivity baseClutterActivity) {
        firebaseRef = new Firebase(fireBaseURL);
        setUpFirebase();
        this.recyclerView = recyclerView;
        this.baseClutterActivity = baseClutterActivity;
    }

    public void postAnonMessageToFirebase(String message) {
        postMessageToFirebase(message, User.AnonUser);
    }

    public void postMessageToFirebase(String message, User user) {
        UserPost userPost = new UserPost(message, user);
        firebaseRef.child(firebaseChildPost).push().setValue(userPost);
        // firebaseRef.child("message").setValue(userTextPost);
    }

    public void postNumUpvotesToFirebase(String firebaseKey, int upvotes) {
        if (firebaseKey != null) {
            System.out.println("firebasekey was okay");
            firebaseRef.child(firebaseChildPost + "/" + firebaseKey + "/upvotes").setValue(upvotes);
        } else {
            System.out.println("firebasekey was null");
        }
    }

    private void setUpFirebase() {
        firebaseRef.child(firebaseChildPost).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //parseDataChangeSnapShot(snapshot);
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
        firebaseRef.child(firebaseChildPost).addChildEventListener(new ChildEventListener() {
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
            Log.i("FirebaseWrapper", "Key: " + snapshot.getKey());
            newUserPost.firebaseKey = snapshot.getKey();

        } catch (FirebaseException fe) {
            newUserPost = new UserPost("Error loading post", User.AnonUser); //todo user different id?
        }
        //System.out.println("Child event: " + newUserPost.toString());
        baseClutterActivity.changeList(recyclerView, newUserPost);
    }
}
