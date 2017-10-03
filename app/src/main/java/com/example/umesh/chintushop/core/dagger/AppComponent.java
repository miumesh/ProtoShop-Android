package com.example.umesh.chintushop.core.dagger;

import com.example.umesh.chintushop.common.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Umesh on 21-09-2017.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                ShoppingCartModule.class
        }
)
public interface AppComponent {
    void inject(MainActivity activity);
}
