package com.ahmedalaa.movieapp.ui.movieListActivity;

import android.widget.ImageView;

import com.ahmedalaa.movieapp.data.Movie;

/**
 * Created by ahmed on 20/09/2017.
 */

interface ClickListener {
    void onItemClick(Movie movie, ImageView poster);
}
