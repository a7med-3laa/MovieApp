package com.ahmedalaa.movieapp.ui.movieDetailActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ahmedalaa.movieapp.R;
import com.ahmedalaa.movieapp.data.Movie;
import com.ahmedalaa.movieapp.data.Review;
import com.ahmedalaa.movieapp.data.Trailer;
import com.ahmedalaa.movieapp.ui.movieListActivity.MovieListActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieDetailFragment extends Fragment implements MovieDetailContractor.View, ClickListener {

    public static final String ARG_ITEM_ID = "item_id";
    private final MovieDetailPresenter movieDetailPresenter;
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

    @BindView(R.id.trailer_progress)
    ProgressBar trailerProgressbar;

    @BindView(R.id.trailer_list)
    RecyclerView trailerList;

    @BindView(R.id.review_progress)
    ProgressBar reviewProgressBar;

    @BindView(R.id.review_list)
    RecyclerView reviewsList;
    @BindView(R.id.no_reviews_txt)
    TextView noReviewsTxt;
    @BindView(R.id.no_trailer_txt)
    TextView noTrailersTxt;
    private Movie movie;
    private TrailerAdapter adapter;
    private ReviewAdapter reviewAdapter;
    private boolean isFavouriteMovie = false;

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
        adapter = new TrailerAdapter(this);
        reviewAdapter = new ReviewAdapter();
        ButterKnife.bind(this, rootView);
        favouriteBtn.setOnClickListener(v -> movieDetailPresenter.addToFavourite(movie));

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
        if (movieDetailPresenter.isAddedToFavourite(movie)) {
            favouriteBtn.setImageResource(R.drawable.ic_favourite);
            isFavouriteMovie = true;
        }
        initViews();
        movieDetailPresenter.fetchTrailer(movie);
        movieDetailPresenter.fetchReviews(movie);
    }

    @Override
    public void changefavouriteImg(boolean favourite) {
        favouriteBtn.setImageResource(favourite ? R.drawable.ic_favourite : R.drawable.ic_unfavourite);

    }

    @Override
    public void notifyFavouriteMsg() {
        Snackbar.make(getActivity().findViewById(R.id.contanier), "Added to Favourite", Snackbar.LENGTH_SHORT).show();
        if (isFavouriteMovie) {
            MovieListActivity.settingChanged = false;
        }
    }

    @Override
    public void notifyUnFavouriteMsg() {
        Snackbar.make(getActivity().findViewById(R.id.contanier), "removed from favourite", Snackbar.LENGTH_SHORT).show();
        if (isFavouriteMovie) {
            MovieListActivity.settingChanged = true;
        }
    }

    @Override
    public void showTrailerProgress(boolean show) {
        trailerProgressbar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        trailerList.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void setTrailerData(List<Trailer> trailers) {
        adapter.setTrailers(trailers);


    }

    @Override
    public void showReviewProgress(boolean show) {
        reviewProgressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        reviewsList.setVisibility(show ? View.INVISIBLE : View.VISIBLE);

    }

    @Override
    public void setReviewData(List<Review> reviews) {
        reviewAdapter.setReviews(reviews);

    }

    @Override
    public void notifyNetworkError(String msg) {
        Snackbar.make(getActivity().findViewById(R.id.contanier), msg, Snackbar.LENGTH_SHORT).show();

    }

    @Override
    public void notifyNoTrailersFound() {
        trailerList.setVisibility(View.GONE);
        trailerProgressbar.setVisibility(View.GONE);
        noTrailersTxt.setVisibility(View.VISIBLE);
    }

    @Override
    public void notifyNoReviewsFound() {
        reviewsList.setVisibility(View.GONE);
        reviewProgressBar.setVisibility(View.GONE);
        noReviewsTxt.setVisibility(View.VISIBLE);
    }

    private void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        trailerList.setLayoutManager(linearLayoutManager);
        trailerList.setAdapter(adapter);
        reviewsList.setLayoutManager(new LinearLayoutManager(getActivity()));

        reviewsList.setAdapter(reviewAdapter);
    }

    @Override
    public void onTrailerItemClick(Trailer trailer) {
        String videoKey = trailer.getKey();
        String youtube_BASE_URL = "https://www.youtube.com/watch?v=";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtube_BASE_URL + videoKey));
        intent.putExtra("force_fullscreen", true);
        startActivity(intent);

    }
}
