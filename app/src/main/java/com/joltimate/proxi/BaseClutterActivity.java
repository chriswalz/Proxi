package com.joltimate.proxi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.Firebase;
import com.joltimate.proxi.Adapters.DividerItemDecoration;
import com.joltimate.proxi.Adapters.FeedRecyclerAdapter;

public abstract class BaseClutterActivity extends AppCompatActivity {
    //protected ListView listView;
    protected RecyclerView recyclerView;
    protected FeedRecyclerAdapter feedRecyclerAdapter;
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

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, R.drawable.drawable_feed_divider));
        feedRecyclerAdapter = new FeedRecyclerAdapter();
        recyclerView.setAdapter(feedRecyclerAdapter);
        firebaseWrapper = new FirebaseWrapper(recyclerView, this);

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
            Log.i("BaseClutter", "settings clicked");
            return true;
        } else if (id == R.id.fake_post) {
            firebaseWrapper.postAnonMessageToFirebase("This is a fake post");
        }
        return super.onOptionsItemSelected(item);
    }

    // protected abstract void postMessageToFirebase(UserPost userPost);
    protected abstract void composeAPost();

    public abstract void changeList(RecyclerView recyclerView, UserPost userPost);

    //protected abstract void parseDataChangeSnapShot(DataSnapshot snapshot);
    //protected abstract void parseChildEvent(DataSnapshot snapshot, String s);

}
