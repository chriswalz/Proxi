package com.joltimate.proxi;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ProxiFeedActivity extends BaseClutterActivity {
    // todo use a recycler view, make a wrapper for reyclerview that holds adapter?
    @Override
    public void changeList(RecyclerView recyclerView, UserPost userPost) {
        feedRecyclerAdapter.addEntry(userPost);

    }


    @Override
    protected void composeAPost() {
        LayoutInflater linf = LayoutInflater.from(this);
        final View inflator = linf.inflate(R.layout.dialog_form, null);
        final EditText et1 = (EditText) inflator.findViewById(R.id.dialog_text);
        Context themedContext = getSupportActionBar().getThemedContext();
        AlertDialog.Builder builder;
        if (themedContext != null) {
            builder = new AlertDialog.Builder(themedContext);
        } else {
            builder = new AlertDialog.Builder(getApplicationContext());
        }
        builder.setTitle("Compose");
        builder.setView(inflator);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String editTextText = et1.getText().toString();
                firebaseWrapper.postAnonMessageToFirebase(editTextText); //todo update userId
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

}

    /*@Override THIS METHOD NO LONGER USED
    protected void parseDataChangeSnapShot(DataSnapshot snapshot) {
        if ( recyclerView != null){
            ArrayList<String> singleStringList = new ArrayList<>();
            System.out.println(snapshot.getValue().toString());
            singleStringList.add(snapshot.getValue().toString());
            changeList(recyclerView, singleStringList);
        }
        //System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
    } */
