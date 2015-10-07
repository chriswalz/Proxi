package com.joltimate.proxi.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.joltimate.proxi.User;
import com.joltimate.proxi.UserPost;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris on 7/9/2015.
 */
public abstract class BaseAdapter extends RecyclerView.Adapter<UserPostHolder> {
    protected ArrayList<UserPost> mDataset;

    public BaseAdapter() {
        mDataset = new ArrayList<UserPost>();
        mDataset.add(new UserPost("No Data Available", User.AnonUser));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addEntry(UserPost entry) {
        mDataset.add(0, entry);
        notifyDataSetChanged();
    }

    public void addList(List<UserPost> entries) {
        //DebuggingTools.logCurrentTask();
        mDataset.clear();
        for (UserPost entry : entries) {
            mDataset.add(0, entry);
        }
        notifyDataSetChanged();
    }

    public abstract UserPostHolder onCreateViewHolder(ViewGroup viewGroup, int i);

    public abstract void onBindViewHolder(UserPostHolder userPostHolder, int i);

}
