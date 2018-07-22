package com.ahmedalaa.movieapp.ui.movieListActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.ahmedalaa.movieapp.R;
import com.ahmedalaa.movieapp.SettingsActivity;
import com.ahmedalaa.movieapp.data.Movie;
import com.ahmedalaa.movieapp.ui.movieDetailActivity.MovieDetailActivity;
import com.ahmedalaa.movieapp.ui.movieDetailActivity.MovieDetailFragment;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListActivity extends AppCompatActivity implements MovieListContractor.View, ClickListener {

    public static boolean settingChanged;
    @BindView(R.id.movie_list_container)
    CoordinatorLayout listContainer;
    @BindView(R.id.main_progressbar)
    ProgressBar mainProgress;
    private MovieListPresenter movieListPresenter;
    private MovieAdapter movieAdapter;
    private View recyclerView;
    private boolean mTwoPane;
    private GridLayoutManager gridLayoutManager;
    private List<Movie> movies;
    private boolean isFavouriteList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        ButterKnife.bind(this);
        movieListPresenter = new MovieListPresenter();
        movieListPresenter.setView(this);
        recyclerView = findViewById(R.id.movie_list);
        setupRecyclerView((RecyclerView) recyclerView);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("d")) {
                movies = Parcels.unwrap(savedInstanceState.getParcelable("d"));
                movieAdapter.setData(movies);
                showProgressBar(false);
            }
        } else
            movieListPresenter.fetchData();
        if (findViewById(R.id.movie_detail_container) != null) {
            mTwoPane = true;
        }
    }

    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        state.putParcelable("d", Parcels.wrap(movies));
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (settingChanged) {
            movieListPresenter.fetchData();
        }
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        if (mTwoPane || getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            gridLayoutManager = new GridLayoutManager(MovieListActivity.this, 3);
        else
            gridLayoutManager = new GridLayoutManager(MovieListActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        movieAdapter = new MovieAdapter(MovieListActivity.this, MovieListActivity.this);
        recyclerView.setAdapter(movieAdapter);
    }


    @Override
    public void showProgressBar(boolean show) {
        mainProgress.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        recyclerView.setVisibility(show ? View.INVISIBLE : View.VISIBLE);

    }

    @Override
    public void showData(List<Movie> movieWrapper) {
        movies = movieWrapper;
        movieAdapter.setData(movieWrapper);

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
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void setFavouriteList(boolean favouriteList) {
        isFavouriteList = false;
    }

    @Override
    public void onItemClick(Movie movie, ImageView poster) {
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
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this, poster, "poster");
            context.startActivity(intent, options.toBundle());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.setting)
            startActivity(new Intent(MovieListActivity.this, SettingsActivity.class));
        return super.onOptionsItemSelected(item);
    }
}

