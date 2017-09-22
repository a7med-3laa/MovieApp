package com.ahmedalaa.movieapp.data;

import java.util.List;

/**
 * Created by ahmed on 20/09/2017.
 */

public class MovieWrapper {
    int id;
    List<Movie> results;

    public MovieWrapper() {
    }

    public MovieWrapper(int id, List<Movie> results) {
        this.id = id;
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
