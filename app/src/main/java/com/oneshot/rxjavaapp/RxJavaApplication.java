package com.oneshot.rxjavaapp;

import android.app.Application;

import com.oneshot.rxjavaapp.service.PhotoRetrofit;

/**
 * Created by brettn on 6/1/17.
 */

public class RxJavaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PhotoRetrofit.getInstance().init(getApplicationContext());
    }
}
