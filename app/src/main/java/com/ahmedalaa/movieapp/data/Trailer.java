package com.ahmedalaa.movieapp.data;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = MyDatabase.class)
public class Trailer extends BaseModel {

    @Column
    public String Movie_id;
    @Column
    @SerializedName("name")
    public String name;
    @Column
    @SerializedName("key")
    public String key;
    @Column
    @SerializedName("type")
    public String type;
    @SerializedName("id")
    @Column
    @PrimaryKey()
    String id; // movie id in API

    public Trailer() {
    }

    public Trailer(String movie_id, String name, String key, String type) {
        Movie_id = movie_id;
        this.name = name;
        this.key = key;
        this.type = type;
    }

    public String getMovie_id() {
        return Movie_id;
    }

    public void setMovie_id(String movie_id) {
        Movie_id = movie_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
