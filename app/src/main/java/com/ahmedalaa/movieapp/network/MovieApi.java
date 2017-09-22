package com.ahmedalaa.movieapp.network;

import com.ahmedalaa.movieapp.data.MovieWrapper;
import com.ahmedalaa.movieapp.data.Review;
import com.ahmedalaa.movieapp.data.Trailer;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {


    @GET("discover/movie")
    Observable<MovieWrapper> getMovieData(@Query("sort_by") String sortType, @Query("api_key") String apiKey, @Query("page") String pageNum);

    @GET("movie/{id}/videos")
    Observable<List<Trailer>> getTrailersforMovie(@Path("id") String MovieID, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Observable<List<Review>> getReviewsforMovie(@Path("id") String MovieID, @Query("api_key") String apiKey);

}