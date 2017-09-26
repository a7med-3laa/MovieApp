package com.ahmedalaa.movieapp.ui.movieDetailActivity;

import com.ahmedalaa.movieapp.Constants;
import com.ahmedalaa.movieapp.data.Movie;
import com.ahmedalaa.movieapp.data.Movie_Table;
import com.ahmedalaa.movieapp.data.Review;
import com.ahmedalaa.movieapp.data.Trailer;
import com.ahmedalaa.movieapp.network.DaggerNetComponent;
import com.ahmedalaa.movieapp.network.MovieApi;
import com.ahmedalaa.movieapp.network.NetModule;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


class MovieDetailPresenter implements MovieDetailContractor.Presenter {
    private final MovieApi movieApi;
    private MovieDetailContractor.View view;

    public MovieDetailPresenter() {
        movieApi = DaggerNetComponent.builder().netModule(new NetModule()).build().getPre();

    }

    @Override
    public void setView(MovieDetailContractor.View view) {
        this.view = view;
    }

    @Override
    public void addToFavourite(Movie movie) {
        if (!movie.getFavourite()) {
            view.notifyFavouriteMsg();
            view.changefavouriteImg(true);
            movie.setFavourite(true);
            movie.save();
        } else {
            movie.setFavourite(false);
            movie.delete();
            view.changefavouriteImg(false);
            view.notifyUnFavouriteMsg();
        }

    }

    @Override
    public void fetchTrailer(Movie movie) {
        view.showTrailerProgress(true);
        if (movie.getFavourite()) {
            List<Trailer> trailers = movie.getTrailersforMovie();
            if (trailers.size() == 0)
                view.notifyNoTrailersFound();
            else {
                view.setTrailerData(trailers);
                view.showTrailerProgress(false);
            }
        } else {
            movieApi.getTrailersforMovie(movie.getId(), Constants.YOUR_API).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(trailers2 -> {
                                if (trailers2.getResults().size() == 0)
                                    view.notifyNoTrailersFound();
                                else {
                                    view.setTrailerData(trailers2.getResults());
                                    view.showTrailerProgress(false);
                                }
                            },
                            error -> {
                                view.notifyNetworkError(error.getMessage());
                                view.showTrailerProgress(false);
                                view.notifyNoTrailersFound();


                            });

        }
    }

    @Override
    public void fetchReviews(Movie movie) {
        view.showReviewProgress(true);
        if (movie.getFavourite()) {
            List<Review> reviews = movie.getReviewsforMovie();
            if (reviews.size() == 0)
                view.notifyNoReviewsFound();
            else {
                view.setReviewData(reviews);
                view.showReviewProgress(false);
            }
        } else {
            movieApi.getReviewsforMovie(movie.getId(), Constants.YOUR_API).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(revies -> {
                                if (revies.getResults().size() == 0)
                                    view.notifyNoReviewsFound();
                                else {

                                    view.setReviewData(revies.getResults());
                                    view.showReviewProgress(false);
                                }
                            },
                            error -> {
                                view.notifyNetworkError(error.getMessage());
                                view.showReviewProgress(false);
                                view.notifyNoReviewsFound();
                            });


        }
    }

    @Override
    public boolean isAddedToFavourite(Movie movie) {
        return SQLite.select().from(Movie.class).where(Movie_Table.id.eq(movie.id)).querySingle() != null;
    }
}
