package com.virtusa.university;

import android.app.Application;

import com.virtusa.university.rest.RestFactory;
import com.virtusa.university.rest.RestInterface;

public class App extends Application {

    public RestInterface rest;
    public static App app;

///////////////////////////////////////////////////////////////////////////
// Lifecycle

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        rest = getRest();
    }


    protected RestInterface getRest() {
        return RestFactory.build();
    }

}
