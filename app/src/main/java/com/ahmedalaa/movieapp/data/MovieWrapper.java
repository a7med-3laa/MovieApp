package com.ahmedalaa.movieapp.data;

import java.util.List;

/**
 * Created by ahmed on 20/09/2017.
 */

public class MovieWrapper {

    List<Movie> results;

    public MovieWrapper() {
    }

    public MovieWrapper(List<Movie> results) {

        this.results = results;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
