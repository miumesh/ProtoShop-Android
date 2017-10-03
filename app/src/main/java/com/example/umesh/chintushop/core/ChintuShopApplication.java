package com.example.umesh.chintushop.core;

import android.app.Application;

import com.example.umesh.chintushop.core.dagger.AppComponent;

/**
 * Created by Umesh on 03-10-2017.
 */

public class ChintuShopApplication extends Application{

    private static AppComponent appComponent;
    private static ChintuShopApplication instance = new ChintuShopApplication();



    @Override
    public void onCreate() {
        super.onCreate();
        getAppComponent();
    }

    private void getAppComponent() {

    }


}
