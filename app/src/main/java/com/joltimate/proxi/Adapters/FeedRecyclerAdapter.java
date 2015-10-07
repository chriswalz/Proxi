package com.joltimate.proxi.Adapters;

import android.support.percent.PercentRelativeLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.joltimate.proxi.R;
import com.joltimate.proxi.UserPost;

import java.util.ArrayList;

/**
 * Created by Chris on 7/9/2015.
 */
public class FeedRecyclerAdapter extends BaseAdapter {
    // using mDataset from super class
    public FeedRecyclerAdapter() {
        super();
    }

    @Override
    public UserPostHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        PercentRelativeLayout percentRelativeLayout = (PercentRelativeLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_post, viewGroup, false);
        UserPostHolder vh = new UserPostHolder(percentRelativeLayout);
        return vh;
    }

    @Override
    public void onBindViewHolder(UserPostHolder userPostHolder, int i) {
        UserPost userPost = mDataset.get(i);
        userPostHolder.mUserPost = userPost;
        String message = userPost.getMessage();
        userPostHolder.mMessageTextView.setText(message);
        int upvotes = userPost.upvotes;
        userPostHolder.mUpvotesTextView.setText("" + upvotes);
    }

    public ArrayList<UserPost> getDataSet() {
        return mDataset;
    }


}

