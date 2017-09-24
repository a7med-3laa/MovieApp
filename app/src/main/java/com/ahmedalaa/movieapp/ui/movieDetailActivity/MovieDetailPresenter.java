package com.ahmedalaa.movieapp.ui.movieDetailActivity;

import com.ahmedalaa.movieapp.data.Movie;


class MovieDetailPresenter implements MovieDetailContractor.Presenter {
    private MovieDetailContractor.View view;

    @Override
    public void setView(MovieDetailContractor.View view) {
        this.view = view;
    }

    @Override
    public void addtoFavourite(Movie movie) {
        view.notifyFavouriteMsg();
        view.changefavouriteImg(true);
        movie.setFavourite(true);
        movie.save();

    }
}
