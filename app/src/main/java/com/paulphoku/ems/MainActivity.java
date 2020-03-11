package com.paulphoku.ems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Welcom Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashActivity = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(splashActivity);
                finish();
            }
        },SPLASH_TIME_OUT);
        //end
    }

    //go to register
    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
