package com.ahmedalaa.movieapp.network;

import android.app.Activity;


import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ahmed on 20/09/2017.
 */
@Singleton
@Component(modules = {NetModule.class})
public interface NetComponent {
    void inject(Activity activity);

    MovieApi getPre();


}
