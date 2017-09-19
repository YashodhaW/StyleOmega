package com.style.yash.styleomega.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.style.yash.styleomega.Database.DatabaseHelper;
import com.style.yash.styleomega.Model.User;
import com.style.yash.styleomega.R;

public class ManageUserActivity extends MainActivity implements View.OnClickListener {

    DatabaseHelper db;

    String id;
    EditText inputName;
    EditText inputEmail;
    TextView displayID;
    TextView displayEmail;
    String email;

    Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_manage, contentFrameLayout);


        submitbtn = (Button) findViewById(R.id.submitbtn);
        inputName = (EditText) findViewById(R.id.inputName);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        displayID = (TextView) findViewById(R.id.displayID);

        submitbtn.setOnClickListener(this);
/*
        displayName = (TextView) findViewById(R.id.displayName);
        displayEmail = (TextView) findViewById(R.id.displayEmail);
*/


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String email = preferences.getString("email", "");


        db = new DatabaseHelper(this);
        User u = db.selectUser(email);

        inputName.setText(u.getName());

        inputEmail.setText(u.getEmail());
        displayID.setText(u.getId());
        id = u.getId();

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.submitbtn) {
            String newName = inputName.getText().toString();
            String newEmail = inputEmail.getText().toString();

            db.UpdateUserDetails(id, newName, newEmail);
            Toast.makeText(getApplicationContext(), "Updated Successfully!", Toast.LENGTH_LONG).show();

            finish();
            Intent homepage = new Intent(ManageUserActivity.this, LoginActivity.class);
            startActivity(homepage);

        }
    }
}
