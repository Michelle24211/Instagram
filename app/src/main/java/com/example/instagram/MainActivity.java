package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ParseUser.getCurrentUser() != null){
            goTimeLine();
        }

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                userSign(user,pass);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                userLogin(user,pass);
            }
        });

    }

    private void userLogin(String user, String pass) {
        ParseUser.logInInBackground(user, pass, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user != null){
                    Toast.makeText(MainActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                    goTimeLine();
                }
                else{
                    Toast.makeText(MainActivity.this, "Login Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goTimeLine() {
        Intent intent = new Intent(MainActivity.this, Timeline.class);
        startActivity(intent);
        finish();
    }

    private void userSign(String user, String pass) {
        ParseUser newUser = new ParseUser();
        newUser.setUsername(user);
        newUser.setPassword(pass);
        newUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(MainActivity.this, "Sign Up Success!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Sign Up Error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}