package com.joltimate.proxi.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by Chris on 7/12/2015.
 */
public abstract class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ViewHolder(View v) {
        super(v);

    }

    @Override
    public void onClick(View v) {
        Log.d("onClick", "Position: " + getAdapterPosition());//getPosition());
    }
}