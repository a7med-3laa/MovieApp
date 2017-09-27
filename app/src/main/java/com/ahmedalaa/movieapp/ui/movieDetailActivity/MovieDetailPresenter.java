package com.ahmedalaa.movieapp.ui.movieDetailActivity;

import com.ahmedalaa.movieapp.Constants;
import com.ahmedalaa.movieapp.data.Movie;
import com.ahmedalaa.movieapp.dataBase.MovieInters;
import com.ahmedalaa.movieapp.network.DaggerNetComponent;
import com.ahmedalaa.movieapp.network.MovieApi;
import com.ahmedalaa.movieapp.network.NetModule;

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
        if (!isAddedToFavourite(movie)) {
            view.notifyFavouriteMsg();
            view.changefavouriteImg(true);
            movie.setFavourite(true);
            MovieInters.addToFavourite(movie, view.getActivityContext());
        } else {
            movie.setFavourite(false);

            MovieInters.removeFromFavourite(movie, view.getActivityContext());
            view.changefavouriteImg(false);
            view.notifyUnFavouriteMsg();
        }

    }

    @Override
    public void fetchTrailer(Movie movie) {
        view.showTrailerProgress(true);
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


    @Override
    public void fetchReviews(Movie movie) {
        view.showReviewProgress(true);

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


    @Override
    public boolean isAddedToFavourite(Movie movie) {
        return MovieInters.isFavourite(movie, view.getActivityContext());
    }
}
