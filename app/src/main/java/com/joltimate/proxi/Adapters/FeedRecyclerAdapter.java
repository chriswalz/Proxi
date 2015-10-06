package com.joltimate.proxi.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // create a new view
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_post, viewGroup, false);
        ViewHolder vh = new UserPostHolder(linearLayout);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        UserPostHolder userPostHolder = (UserPostHolder) viewHolder;
        String message = mDataset.get(i).getMessage();
        userPostHolder.mTextView.setText(message);
    }

    public ArrayList<UserPost> getDataSet() {
        return mDataset;
    }


}

