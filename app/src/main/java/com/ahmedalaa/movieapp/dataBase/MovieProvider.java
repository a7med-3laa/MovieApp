package com.ahmedalaa.movieapp.dataBase;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MovieProvider extends ContentProvider {
    private static final int Movie = 100;
    private static final int MovieID = 101;
    private static final UriMatcher uriMatcher = buildUri();
    static MovieDatabaseHelper helper;


    public MovieProvider() {
    }

    private static UriMatcher buildUri() {
        UriMatcher uri = new UriMatcher(UriMatcher.NO_MATCH);
        uri.addURI(MovieContractor.AUTHuRL, MovieContractor.MoviePath, Movie);
        uri.addURI(MovieContractor.AUTHuRL, MovieContractor.MoviePath + "/*", MovieID);

        return uri;

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        int i = uriMatcher.match(uri);
        int count = 0;
        SQLiteDatabase db = helper.getWritableDatabase();

        switch (i) {
            case MovieID:
                String id = uri.getPathSegments().get(1);
                db.delete(MovieContractor.MovieEntry.TABLE_NAME, MovieContractor.MovieEntry._ID + " =? ", new String[]{id});
                count++;

        }
        return count;
    }

    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case Movie:
                return MovieContractor.CONTENT_TYPE;
            case MovieID:
                return MovieContractor.CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        long id;
        Uri returnUri = null;
        switch (match) {
            case Movie:
                id = db.insert(MovieContractor.MovieEntry.TABLE_NAME, null, values);
                if (id > 0)
                    returnUri = MovieContractor.MovieEntry.buildMovieUri(String.valueOf(id));
                break;
        }
        return returnUri;
    }


    @Override
    public boolean onCreate() {
        helper = new MovieDatabaseHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase sqLiteDatabase = helper.getReadableDatabase();
        int match = uriMatcher.match(uri);
        Cursor cursor = null;
        switch (match) {
            case Movie:

                cursor = sqLiteDatabase.query(MovieContractor.MovieEntry.TABLE_NAME, null, null, null, null, null, null);
                break;
            case MovieID:
                String id = uri.getPathSegments().get(1);
                cursor = sqLiteDatabase.query(MovieContractor.MovieEntry.TABLE_NAME, null, MovieContractor.MovieEntry._ID + " = ?", new String[]{id}, null, null, null);
                break;

        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
