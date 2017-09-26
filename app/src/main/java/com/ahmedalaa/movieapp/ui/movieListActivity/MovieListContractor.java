package com.ahmedalaa.movieapp.ui.movieListActivity;

import android.content.Context;

import com.ahmedalaa.movieapp.data.Movie;

import java.util.List;


interface MovieListContractor {
    interface View {
        void showProgressBar(boolean show);

        void showData(List<Movie> movieWrapper);

        void notifyOffline();
        void notifyNetworkError(String msg);

        Context getActivityContext();

        void setFavouriteList(boolean favouriteList);

    }

    interface Presenter {
        void fetchData();
        void  setView(View view);
    }
}
