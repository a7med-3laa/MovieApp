package com.ahmedalaa.movieapp.ui.movieListActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.ahmedalaa.movieapp.R;
import com.ahmedalaa.movieapp.data.Movie;
import com.ahmedalaa.movieapp.data.MovieWrapper;
import com.ahmedalaa.movieapp.ui.movieDetailActivity.MovieDetailActivity;
import com.ahmedalaa.movieapp.ui.movieDetailActivity.MovieDetailFragment;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends AppCompatActivity implements MovieListContractor.View, ClickListener{

    private boolean mTwoPane;

    MovieListPresenter movieListPresenter;

    @BindView(R.id.movie_list_container)
    CoordinatorLayout listContainer;

    @BindView(R.id.main_progressbar)
    ProgressBar mainProgress;

    MovieAdapter movieAdapter;
    View recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        ButterKnife.bind(this);
        movieListPresenter=new MovieListPresenter();
        movieListPresenter.setView(this);
        recyclerView = findViewById(R.id.movie_list);
        setupRecyclerView((RecyclerView) recyclerView);
        movieListPresenter.fetchData();
        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MovieListActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        movieAdapter = new MovieAdapter(MovieListActivity.this,MovieListActivity.this);
        recyclerView.setAdapter(movieAdapter);
    }


    @Override
    public void showProgressBar(boolean show) {
        mainProgress.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        recyclerView.setVisibility(show? View.INVISIBLE:View.VISIBLE);

    }

    @Override
    public void showData(MovieWrapper movieWrapper) {
        movieAdapter.setData(movieWrapper.getResults());

    }

    @Override
    public void notifyOffline() {
        Snackbar.make(listContainer, R.string.notify_error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void notifyNetworkError(String msg) {

        Snackbar.make(listContainer, msg, Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void onItemClick(Movie movie) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieDetailFragment.ARG_ITEM_ID, Parcels.wrap(movie));
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();
        } else {
            Context context = this;
            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieDetailFragment.ARG_ITEM_ID, Parcels.wrap(movie));

            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra(MovieDetailFragment.ARG_ITEM_ID, arguments);

            context.startActivity(intent);
        }
    }
}
