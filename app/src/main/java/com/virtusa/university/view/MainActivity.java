package com.virtusa.university.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.virtusa.university.R;
import com.virtusa.university.view.login.LoginActivity;

public class MainActivity extends BaseActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showSplash();
    }
    private void showSplash() {
        /* New Handler to start the login-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the login-Activity. */

                    Intent mainIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(mainIntent);
                    finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }



}