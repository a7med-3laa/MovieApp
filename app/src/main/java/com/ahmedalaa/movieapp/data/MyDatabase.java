package com.ahmedalaa.movieapp.data;

import android.net.Uri;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.annotation.provider.ContentProvider;

/**
 * Created by ahmed on 20/09/2017.
 */
@ContentProvider(authority = MyDatabase.AUTHORITY,
        database = MyDatabase.class,
        baseContentUri = MyDatabase.BASE_CONTENT_URI)
@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyDataBase";

    public static final int VERSION = 1;
    public static final String AUTHORITY = "com.ahmed.alaa.movieapp.provider";

    public static final String BASE_CONTENT_URI = "content://";

    public static Uri buildUri(String... paths) {
        Uri.Builder builder = Uri.parse(MyDatabase.BASE_CONTENT_URI + MyDatabase.AUTHORITY).buildUpon();
        for (String path : paths) {
            builder.appendPath(path);
        }
        return builder.build();
    }


}


