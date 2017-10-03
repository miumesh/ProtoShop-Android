package com.example.umesh.chintushop.core.dagger;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Umesh on 03-10-2017.
 */
@Module
public class BusModule {
    @Provides @Singleton
    public Bus provideBus(){
        return new Bus();
    }
}
