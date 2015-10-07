package com.joltimate.proxi.Adapters;

import android.support.percent.PercentRelativeLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.joltimate.proxi.BaseClutterActivity;
import com.joltimate.proxi.UserPost;

public class UserPostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    // each data item is just a string in this case
    public PercentRelativeLayout percentRelativeLayout;
    public TextView mMessageTextView;
    public TextView mUpvotesTextView;
    public ImageButton mUpvoteImageButton;
    public UserPost mUserPost;

    public UserPostHolder(PercentRelativeLayout percentRelativeLayout) {
        super(percentRelativeLayout);
        this.percentRelativeLayout = percentRelativeLayout;

        this.mMessageTextView = (TextView) percentRelativeLayout.getChildAt(0);
        this.mUpvotesTextView = (TextView) percentRelativeLayout.getChildAt(2);
        this.mUpvoteImageButton = (ImageButton) percentRelativeLayout.getChildAt(1);

        this.mMessageTextView.setOnClickListener(this);
        this.mUpvoteImageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof TextView) {
            Log.d("onClick", "Message was clicked, position is : " + getAdapterPosition());//getPosition());
        } else if (v instanceof ImageButton) {
            //todo use BusApp instead of mainAcitivy to get reference to firebaseref,
            // todo also separate this code from userPostHolder
            // todo postNumUpvotes should increment, not setValue!
            mUserPost.upvotes++;
            mUpvotesTextView.setText("" + mUserPost.upvotes);
            BaseClutterActivity.firebaseWrapper.postNumUpvotesToFirebase(mUserPost.firebaseKey, mUserPost.upvotes);
            Log.d("onClick", "Upvate ImageButton clicked");

        }
    }
}
