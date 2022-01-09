package com.ALDev.kesemrealestate;

import android.app.Application;

import com.ALDev.kesemrealestate.MySP.MSP;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MSP.initHelper(this);
    }
}