package com.style.yash.styleomega;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.style.yash.styleomega.Database.DatabaseHelper;

public class ManageActivity extends AppCompatActivity {

    DatabaseHelper db;

    String mid;
    EditText inputName;
    EditText inputEmail;
    TextView displayName;
    TextView displayEmail;

    Button submitbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        submitbtn = (Button) findViewById(R.id.submitbtn);
        inputName = (EditText) findViewById(R.id.inputName);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        displayName = (TextView) findViewById(R.id.displayName);
        displayEmail = (TextView) findViewById(R.id.displayEmail);

    }
}
