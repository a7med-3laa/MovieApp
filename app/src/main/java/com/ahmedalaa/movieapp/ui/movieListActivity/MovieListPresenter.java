package com.ahmedalaa.movieapp.ui.movieListActivity;

import android.preference.PreferenceManager;

import com.ahmedalaa.movieapp.Constants;
import com.ahmedalaa.movieapp.data.Movie;
import com.ahmedalaa.movieapp.network.DaggerNetComponent;
import com.ahmedalaa.movieapp.network.MovieApi;
import com.ahmedalaa.movieapp.network.NetModule;
import com.ahmedalaa.movieapp.util.NetworkUtil;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ahmed on 20/09/2017.
 */

class MovieListPresenter implements MovieListContractor.Presenter {

    private final MovieApi movieApi;
    private MovieListContractor.View view;

    MovieListPresenter() {
        movieApi = DaggerNetComponent.builder().netModule(new NetModule()).build().getPre();
    }


    @Override
    public void fetchData() {
        view.showProgressBar(true);
        String movieSortType = PreferenceManager.getDefaultSharedPreferences(view.getActivityContext()).
                getString("movie_sort", "top_rated");

        if (movieSortType.equals("favourite")) {
            SQLite.select().from(Movie.class).async().queryListResultCallback((transaction, tResult) -> {
                view.showData(tResult);
                view.showProgressBar(false);

            }).execute();
        } else if (!NetworkUtil.checkInternetConnection(view.getActivityContext())) {
            SQLite.select().from(Movie.class).async().queryListResultCallback((transaction, tResult) -> {
                view.showData(tResult);
                view.showProgressBar(false);
                view.notifyNetworkError("No Connection, Only can browse favourite movies");
                view.setFavouriteList(true);
            }).execute();

        } else
            movieApi.getMovieData(movieSortType, Constants.YOUR_API, String.valueOf(1))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mMovies -> {
                        view.showData(mMovies.getResults());
                        view.setFavouriteList(false);
                        view.showProgressBar(false);

                    }, error -> {
                        view.notifyNetworkError(error.getMessage());

                        view.showProgressBar(false);
                    });


    }

    @Override
    public void setView(MovieListContractor.View view) {
        this.view = view;
    }
}

