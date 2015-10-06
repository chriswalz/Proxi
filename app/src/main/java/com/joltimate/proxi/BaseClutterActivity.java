package com.joltimate.proxi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.ArrayList;

public abstract class BaseClutterActivity extends AppCompatActivity {
    protected ListView listView;
    protected FirebaseWrapper firebaseWrapper;

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

        listView = (ListView) findViewById(R.id.list_view);
        firebaseWrapper = new FirebaseWrapper(listView, this);

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

    // protected abstract void postMessageToFirebase(UserPost userPost);
    protected abstract void composeAPost();

    public abstract void changeList(ListView listView, ArrayList<String> list);

    protected abstract void parseDataChangeSnapShot(DataSnapshot snapshot);
    //protected abstract void parseChildEvent(DataSnapshot snapshot, String s);

}
