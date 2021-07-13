package com.virtusa.university.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.virtusa.university.R;
import com.virtusa.university.view.BaseActivity;
import com.virtusa.university.view.university.activity.UniversityActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void doLogin(View view) {
        Intent mainIntent = new Intent(LoginActivity.this, UniversityActivity.class);
        startActivity(mainIntent);
        finish();
    }

}
