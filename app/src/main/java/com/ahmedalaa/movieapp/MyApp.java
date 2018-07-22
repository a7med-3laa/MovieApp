package com.ahmedalaa.movieapp;

import android.app.Application;

import com.facebook.stetho.Stetho;


/**
 * Created by ahmed on 20/09/2017.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
