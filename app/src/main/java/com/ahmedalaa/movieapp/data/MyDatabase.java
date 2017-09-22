package com.ahmedalaa.movieapp.data;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by ahmed on 20/09/2017.
 */
@Database(name = MyDatabase.NAME, version = MyDatabase.VERSION)
public class MyDatabase {
    public static final String NAME = "MyDataBase";

    public static final int VERSION = 1;
}
