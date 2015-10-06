package com.joltimate.proxi.Adapters;

import android.widget.LinearLayout;
import android.widget.TextView;

public class UserPostHolder extends ViewHolder {
    // each data item is just a string in this case
    public LinearLayout mLinearLayout;
    public TextView mTextView;

    public UserPostHolder(LinearLayout linearLayout) {
        super(linearLayout);
        this.mLinearLayout = linearLayout;
        this.mTextView = (TextView) linearLayout.getChildAt(0);
        linearLayout.setOnClickListener(this);
    }
}
