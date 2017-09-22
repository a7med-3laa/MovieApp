package com.ahmedalaa.movieapp.ui.movieDetailActivity;

import com.ahmedalaa.movieapp.data.Movie;

interface MovieDetailContractor {
    interface View {
        void changefavouriteImg(boolean change);

        void notifyFavouriteMsg();
    }

    interface Presenter {
        void setView(View view);

        void addtoFavourite(Movie movie);
    }
}
