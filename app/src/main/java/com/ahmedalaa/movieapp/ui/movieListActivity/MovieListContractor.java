package com.ahmedalaa.movieapp.ui.movieListActivity;

import android.content.Context;

import com.ahmedalaa.movieapp.data.MovieWrapper;


interface MovieListContractor {
    interface View {
        void showProgressBar(boolean show);

        void showData(MovieWrapper movieWrapper);

        void notifyOffline();
        void notifyNetworkError(String msg);

        Context getActivityContext();

    }

    interface Presenter {
        void fetchData();
        void  setView(View view);
    }
}
