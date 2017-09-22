package com.ahmedalaa.movieapp.ui.movieListActivity;

import android.util.Log;

import com.ahmedalaa.movieapp.Constants;
import com.ahmedalaa.movieapp.network.DaggerNetComponent;
import com.ahmedalaa.movieapp.network.MovieApi;
import com.ahmedalaa.movieapp.network.NetModule;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by ahmed on 20/09/2017.
 */

class MovieListPresenter implements MovieListContractor.Presenter {

    MovieListContractor.View view;

    MovieApi movieApi;

    public MovieListPresenter() {
        movieApi = DaggerNetComponent.builder().netModule(new NetModule()).build().getPre();
    }


    @Override
    public void fetchData() {
        view.showProgressBar(true);
        movieApi.getMovieData("popularity.desc", Constants.YOUR_API, String.valueOf(1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mMovies -> {
                    view.showData(mMovies);

                    view.showProgressBar(false);

                    Log.i("fETCH","GET DATA");
                }, error -> {
                    view.notifyNetworkError(error.getMessage());

                    view.showProgressBar(false);
                    Log.i("fETCH","eRROR");
                });


    }

    @Override
    public void setView(MovieListContractor.View view) {
        this.view = view;
    }
}

