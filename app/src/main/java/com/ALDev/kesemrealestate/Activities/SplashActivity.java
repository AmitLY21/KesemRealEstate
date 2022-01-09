package com.ALDev.kesemrealestate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.ALDev.kesemrealestate.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

// Using handler with postDelayed called runnable run method

            @Override

            public void run() {

                Intent i = new Intent(SplashActivity.this, HomeActivity.class);

                startActivity(i);

                // close this activity

                finish();

            }

        }, 3100); // wait for 5 seconds
    }
}