package com.ahmedalaa.movieapp.network;

import com.ahmedalaa.movieapp.data.MovieWrapper;
import com.ahmedalaa.movieapp.data.ReviewWrapper;
import com.ahmedalaa.movieapp.data.TrailerWrapper;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {


    @GET("movie/{type}")
    Observable<MovieWrapper> getMovieData(@Path("type") String sortType, @Query("api_key") String apiKey, @Query("page") String pageNum);

    @GET("movie/{id}/videos")
    Observable<TrailerWrapper> getTrailersforMovie(@Path("id") String MovieID, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Observable<ReviewWrapper> getReviewsforMovie(@Path("id") String MovieID, @Query("api_key") String apiKey);

}