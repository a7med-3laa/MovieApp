package com.ahmedalaa.movieapp.data;

import java.util.List;

/**
 * Created by ahmed on 24/09/2017.
 */

public class ReviewWrapper {
    List<Review> results;

    public ReviewWrapper(List<Review> results) {
        this.results = results;
    }

    public List<Review> getResults() {
        return results;
    }

    public void setResults(List<Review> results) {
        this.results = results;
    }
}
