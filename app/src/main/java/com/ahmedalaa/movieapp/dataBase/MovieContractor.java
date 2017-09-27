package com.ahmedalaa.movieapp.dataBase;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by ahmed on 27/09/2017.
 */

public class MovieContractor {
    public static final String AUTHuRL = "com.ahmedalaa.movieapp";
    public static final String MoviePath = "movies";
    public static final String CONTENT_TYPE =
            ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHuRL + "/" + MoviePath;
    public static final String CONTENT_ITEM_TYPE =
            ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + AUTHuRL + "/" + MoviePath;
    private static final Uri BaseURL = Uri.parse("content://" + AUTHuRL);

    public static final class MovieEntry implements BaseColumns {


        public static final Uri Content_uri = BaseURL.buildUpon().appendPath(MoviePath).build();


        public static final String TABLE_NAME = "Movies";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_POSTER = "poster";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_realiseDate = "date";
        public static final String COLUMN_Vote = "vote";

        public static Uri buildMovieUri(String id) {
            return Content_uri.buildUpon().appendPath(id).build();
        }
    }

}
