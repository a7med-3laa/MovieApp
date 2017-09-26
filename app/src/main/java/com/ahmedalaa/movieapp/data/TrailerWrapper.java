package com.ahmedalaa.movieapp.data;

import java.util.List;

/**
 * Created by ahmed on 24/09/2017.
 */

public class TrailerWrapper {
    List<Trailer> results;

    public TrailerWrapper(List<Trailer> results) {
        this.results = results;
    }

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }
}
