package com.ahmedalaa.movieapp.ui.movieDetailActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmedalaa.movieapp.R;
import com.ahmedalaa.movieapp.data.Movie;
import com.ahmedalaa.movieapp.ui.movieListActivity.MovieListActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    Movie movie;

    @BindView(R.id.movie_name)
    TextView movieNameTV;

    @BindView(R.id.movie_rating)
    TextView movieRatingTV;

    @BindView(R.id.movie_desc)
    TextView movieDescTV;

    @BindView(R.id.release_date)
    TextView movieRealiseDateTV;
    @BindView(R.id.movie_img)
    ImageView movieImg;
    public MovieDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            movie = Parcels.unwrap(getArguments().getParcelable(ARG_ITEM_ID));
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(movie.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);

        ButterKnife.bind(this,rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieDescTV.setText(movie.getOverview());
        movieNameTV.setText(movie.getName());
        movieRatingTV.setText(movie.getVoteAverege());
        movieRealiseDateTV.setText(movie.getReleasedate());
        Picasso.with(getContext()).load( movie.getPosterPath()).into(movieImg);
    }
}
