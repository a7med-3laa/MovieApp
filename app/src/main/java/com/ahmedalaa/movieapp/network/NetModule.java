package com.ahmedalaa.movieapp.network;

import com.ahmedalaa.movieapp.Constants;
import com.ahmedalaa.movieapp.util.DBFlowExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ahmed on 21/07/2017.
 */
@Singleton
@Module
public class NetModule {
    public final static String API_KEY = Constants.YOUR_API;
    private static final String BASE_URL = "http://api.themoviedb.org/3/";

    @Provides
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new DBFlowExclusionStrategy());
        return gsonBuilder.create();
    }

    @Provides
    public Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @Provides
    public MovieApi provideApi(Retrofit retrofit) {
        return retrofit.create(MovieApi.class);

    }
}
