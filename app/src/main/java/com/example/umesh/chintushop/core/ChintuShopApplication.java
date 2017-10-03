package com.example.umesh.chintushop.core;

import android.app.Application;

import com.example.umesh.chintushop.core.dagger.AppComponent;
import com.example.umesh.chintushop.core.dagger.AppModule;
import com.example.umesh.chintushop.core.dagger.DaggerAppComponent;

/**
 * Created by Umesh on 03-10-2017.
 */

public class ChintuShopApplication extends Application{

    private static AppComponent appComponent;
    private static ChintuShopApplication instance = new ChintuShopApplication();

    public static ChintuShopApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getAppComponent();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;

    }


}
