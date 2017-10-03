package com.example.umesh.chintushop.core.dagger;

import android.content.Context;

import com.example.umesh.chintushop.core.ChintuShopApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Umesh on 21-09-2017.
 */
@Module
public class AppModule {

    private final ChintuShopApplication app;

    public AppModule(ChintuShopApplication app) {
        this.app = app;
    }

    @Provides @Singleton
    public ChintuShopApplication provideApp(){
        return app;
    }

    @Provides @Singleton
    public Context provideContext(){
        return app;
    }
}
