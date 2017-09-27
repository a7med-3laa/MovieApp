package com.ahmedalaa.movieapp.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ahmed on 27/09/2017.
 */

public class MovieDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Movies.db";

    public MovieDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String MOVIES_TABLE = "CREATE TABLE " + MovieContractor.MovieEntry.TABLE_NAME + " ( " +
                MovieContractor.MovieEntry._ID + " TEXT PRIMARY KEY , " + MovieContractor.MovieEntry.COLUMN_NAME +
                " TEXT NOT NULL, " + MovieContractor.MovieEntry.COLUMN_OVERVIEW +
                " TEXT NOT NULL, " + MovieContractor.MovieEntry.COLUMN_POSTER +
                " TEXT NOT NULL, " + MovieContractor.MovieEntry.COLUMN_realiseDate +
                " TEXT NOT NULL, " + MovieContractor.MovieEntry.COLUMN_Vote + " TEXT NOT NULL ); ";


        sqLiteDatabase.execSQL(MOVIES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }
}
