package com.ahmedalaa.movieapp.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.ahmedalaa.movieapp.data.Movie;

import java.util.ArrayList;
import java.util.List;


public class MovieInters {
    public static final String MOVIEINTERNS = "MOVIEE";

    public static List<Movie> getMovies(Context context) {
        List<Movie> movies = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MovieContractor.MovieEntry.Content_uri, null, null, null, null);
        cursor.moveToFirst();
        Log.i(MOVIEINTERNS, cursor.getCount() + " ");
        do {
            String title = cursor.getString(cursor.getColumnIndex(MovieContractor.MovieEntry.COLUMN_NAME));

            String vote = cursor.getString(cursor.getColumnIndex(MovieContractor.MovieEntry.COLUMN_Vote));

            String desc = cursor.getString(cursor.getColumnIndex(MovieContractor.MovieEntry.COLUMN_OVERVIEW));
            String releaseDate = cursor.getString(cursor.getColumnIndex(MovieContractor.MovieEntry.COLUMN_realiseDate));
            String poster = cursor.getString(cursor.getColumnIndex(MovieContractor.MovieEntry.COLUMN_POSTER));
            String id = cursor.getString(cursor.getColumnIndex(MovieContractor.MovieEntry._ID));

            Movie movie = new Movie(id, title, poster, releaseDate, desc, vote);
            movies.add(movie);
        } while (cursor.moveToNext());
        cursor.close();
        return movies;
    }

    public static void addToFavourite(Movie movie, Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieContractor.MovieEntry._ID, movie.getId());
        contentValues.put(MovieContractor.MovieEntry.COLUMN_NAME, movie.getName());
        contentValues.put(MovieContractor.MovieEntry.COLUMN_Vote, movie.getVoteAverege());
        contentValues.put(MovieContractor.MovieEntry.COLUMN_realiseDate, movie.getReleasedate());
        contentValues.put(MovieContractor.MovieEntry.COLUMN_POSTER, movie.getPosterPath());
        contentValues.put(MovieContractor.MovieEntry.COLUMN_OVERVIEW, movie.getOverview());
        Uri uRi = context.getContentResolver().insert(MovieContractor.MovieEntry.Content_uri, contentValues);

        Log.i(MOVIEINTERNS, uRi.toString());

    }

    public static void removeFromFavourite(Movie movie, Context context) {
        context.getContentResolver().delete(MovieContractor.MovieEntry.buildMovieUri(movie.getId()),
                null, null);
    }

    public static boolean isFavourite(Movie movie, Context context) {
        return context.getContentResolver().query(MovieContractor.MovieEntry.buildMovieUri(movie.getId()), null, null, null, null)
                .getCount() > 0;
    }


}
