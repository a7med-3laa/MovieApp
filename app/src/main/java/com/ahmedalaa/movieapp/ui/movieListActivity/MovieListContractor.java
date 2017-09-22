package com.ahmedalaa.movieapp.ui.movieListActivity;

import com.ahmedalaa.movieapp.data.Movie;
import com.ahmedalaa.movieapp.data.MovieWrapper;

import java.util.List;

/**
 * Created by ahmed on 20/09/2017.
 */

public interface MovieListContractor {
    interface View {
        void showProgressBar(boolean show);

        void showData(MovieWrapper movieWrapper);

        void notifyOffline();
        void notifyNetworkError(String msg);
    }

    interface Presenter {
        void fetchData();
        void  setView(View view);
    }
}
