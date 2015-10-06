package com.joltimate.proxi;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;

import java.util.ArrayList;

public class ProxiFeedActivity extends BaseClutterActivity {
    @Override
    public void changeList(ListView listView, ArrayList<String> list) {
        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }
    @Override
    protected void parseDataChangeSnapShot(DataSnapshot snapshot) {
        if ( listView != null){
            ArrayList<String> singleStringList = new ArrayList<>();
            System.out.println(snapshot.getValue().toString());
            singleStringList.add(snapshot.getValue().toString());
            changeList(listView, singleStringList);
        }
        //System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
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
        builder.setMessage("Lorem ipsum dolor ....");
        builder.setView(inflator);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String editTextText = et1.getText().toString();
                firebaseWrapper.postMessageToFirebase(new UserPost(editTextText, "1234")); //todo update userId
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
