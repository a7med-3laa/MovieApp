package com.ahmedalaa.movieapp.ui.movieDetailActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedalaa.movieapp.R;
import com.ahmedalaa.movieapp.data.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieDetailFragment extends Fragment implements MovieDetailContractor.View {

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
    @BindView(R.id.fab)
    FloatingActionButton favouriteBtn;
    MovieDetailPresenter movieDetailPresenter;

    public MovieDetailFragment() {
        movieDetailPresenter = new MovieDetailPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            movie = Parcels.unwrap(getArguments().getParcelable(ARG_ITEM_ID));

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);
        movieDetailPresenter.setView(this);
        ButterKnife.bind(this, rootView);
        favouriteBtn.setOnClickListener(v -> {
            movieDetailPresenter.addtoFavourite(movie);
        });

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieDescTV.setText(movie.getOverview());
        movieNameTV.setText(movie.getName());
        movieRatingTV.setText(String.format("%s/10", movie.getVoteAverege()));
        movieRealiseDateTV.setText(movie.getReleasedate());
        Picasso.with(getContext()).load(movie.getPosterPath()).into(movieImg);
    }

    @Override
    public void changefavouriteImg(boolean favourite) {
        favouriteBtn.setImageResource(favourite ? R.drawable.ic_favourite : R.drawable.ic_unfavourite);

    }

    @Override
    public void notifyFavouriteMsg() {
        Toast.makeText(getActivity(), "Not Implemented yet", Toast.LENGTH_SHORT).show();
    }

}
