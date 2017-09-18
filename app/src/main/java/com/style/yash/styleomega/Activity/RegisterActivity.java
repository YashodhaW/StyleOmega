package com.style.yash.styleomega.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.style.yash.styleomega.Database.DatabaseHelper;
import com.style.yash.styleomega.Model.User;
import com.style.yash.styleomega.R;

public class RegisterActivity extends AppCompatActivity {

    EditText nameInput, emailInput, passwordInput;

    Button btnRegister;
    String name;
    String email;
    String password;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        nameInput = (EditText) findViewById(R.id.nameInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        db = new DatabaseHelper(this);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = nameInput.getText().toString();
                password = passwordInput.getText().toString();
                email = emailInput.getText().toString();

                User user = new User();
                user.setName(name);
                user.setPassword(password);
                user.setEmail(email);

                db.addUser(user);
                Toast.makeText(getApplicationContext(), "Registered Successfully!", Toast.LENGTH_LONG).show();

                finish();
                Intent homepage = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(homepage);


            }
        });
    }


}
