package com.example.brewbuddycs380;

import java.sql.*;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;


public class MainActivity extends AppCompatActivity{

    @Override
    // first part that runs when app runs
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // changes the status bar color so it looks prettier
        if (Build.VERSION.SDK_INT >= 21)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.statusbar_main));
        }

        // sets the app view to the main activity (login page)
        setContentView(R.layout.activity_main);


        // find login button from main activity
        Button loginBtn = (Button) findViewById(R.id.login);
        // set a click listener on that button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // right now this just goes to the get started page
                startActivity(new Intent(MainActivity.this, QuestionActivity.class));

            }
        });

        TextView createAccountTxt = findViewById(R.id.createAccount);
        // set a click listener on that text
        createAccountTxt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // go to create account activity
                startActivity(new Intent(MainActivity.this, CreateAccount.class));

            }
        });
    }
}