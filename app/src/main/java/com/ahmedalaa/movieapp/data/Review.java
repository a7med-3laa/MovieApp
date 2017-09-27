package com.ahmedalaa.movieapp.data;

import com.google.gson.annotations.SerializedName;


/**
 * Created by ahmed on 20/09/2017.
 */

public class Review {

    @SerializedName("id")


    public String id; // movie id in API


    public String author;


    public String content;


    public String Uri;


    public String Movie_id;

    public Review() {
    }

    public Review(String movie_id, String author, String content, String uri) {
        Movie_id = movie_id;
        this.author = author;
        this.content = content;
        Uri = uri;
    }

    public String getMovie_id() {
        return Movie_id;
    }

    public void setMovie_id(String movie_id) {
        Movie_id = movie_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }
}
