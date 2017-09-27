package com.ahmedalaa.movieapp.ui.movieDetailActivity;

import android.content.Context;

import com.ahmedalaa.movieapp.data.Movie;
import com.ahmedalaa.movieapp.data.Review;
import com.ahmedalaa.movieapp.data.Trailer;

import java.util.List;

interface MovieDetailContractor {
    interface View {
        void changefavouriteImg(boolean change);

        void notifyFavouriteMsg();

        void notifyUnFavouriteMsg();


        void showTrailerProgress(boolean show);

        void setTrailerData(List<Trailer> trailers);

        void showReviewProgress(boolean show);

        void setReviewData(List<Review> reviews);

        void notifyNetworkError(String msg);

        void notifyNoTrailersFound();

        void notifyNoReviewsFound();

        Context getActivityContext();
    }

    interface Presenter {
        void setView(View view);

        void addToFavourite(Movie movie);

        void fetchTrailer(Movie movie);

        void fetchReviews(Movie movie);

        boolean isAddedToFavourite(Movie movie);
    }
}
