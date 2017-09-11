package com.style.yash.styleomega;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.idescout.sql.SqlScoutServer;
import com.style.yash.styleomega.Database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    String emailValue;
    Context context;
    EditText password;
    String passwordValue;
    DatabaseHelper db;
    Button loginbtn;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SqlScoutServer.create(this, getPackageName());


        loginbtn = (Button) findViewById(R.id.loginbtn);
        signup = (Button) findViewById(R.id.signup);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        context = this;
        db = new DatabaseHelper(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailValue = email.getText().toString();
                passwordValue = password.getText().toString();


                if (db.checkUser(emailValue, passwordValue)) {

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(context, "incorrect!", Toast.LENGTH_LONG).show();
                }


            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
