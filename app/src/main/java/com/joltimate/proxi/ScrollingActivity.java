package com.joltimate.proxi;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class ScrollingActivity extends AppCompatActivity {
    private final String fireBaseURL = "https://proxi.firebaseio.com/";
    private Firebase myFirebaseRef;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                composeAPost();
            }
        });
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase(fireBaseURL);
        myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //parseDataChangeSnapShot(snapshot);
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
        myFirebaseRef.child("message").addChildEventListener(new ChildEventListener() {
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
        listView = (ListView) findViewById(R.id.list_view);
        setUpList(listView);



        // lolo lo l
    }
    private void parseChildEvent(DataSnapshot snapshot, String s){ // todo check if null, check if is indeed a string
        String newPost = snapshot.getValue(String.class);
        System.out.println("Child event: " + newPost);
        ArrayList<String> singleStringList = new ArrayList<>();
        singleStringList.add(newPost);
        changeList(listView, singleStringList);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void parseDataChangeSnapShot(DataSnapshot snapshot){
        if ( listView != null){
            ArrayList<String> singleStringList = new ArrayList<>();
            System.out.println(snapshot.getValue().toString());
            singleStringList.add(snapshot.getValue().toString());
            changeList(listView, singleStringList);
        }
        //System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
    }

    private void setUpList(ListView listView){
        String[] values = new String[] { "Android err", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
                "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
                "Android", "iPhone", "WindowsMobile" };
        final ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        changeList(listView, list);

    }
    private void changeList(ListView listView, ArrayList<String> list){
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    private void composeAPost() {
        LayoutInflater linf = LayoutInflater.from(this);
        final View inflator = linf.inflate(R.layout.dialog_form, null);
        final EditText et1 = (EditText) inflator.findViewById(R.id.dialog_text);
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getSupportActionBar().getThemedContext());
        builder.setTitle("Compose");
        builder.setMessage("Lorem ipsum dolor ....");
        builder.setView(inflator);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String editTextText = et1.getText().toString();
                postMessageToFirebase(editTextText);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("Cancel");
            }
        });
        builder.show();
        //InputMethodManager imm = (InputMethodManager)getSystemService(Service.INPUT_METHOD_SERVICE);
        //imm.showSoftInput()
    }

    private void postMessageToFirebase(String userTextPost) {
        myFirebaseRef.child("message").push().setValue(userTextPost);
       // myFirebaseRef.child("message").setValue(userTextPost);
    }
}
