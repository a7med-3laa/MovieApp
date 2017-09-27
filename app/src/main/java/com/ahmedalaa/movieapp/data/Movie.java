package com.ahmedalaa.movieapp.data;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by ahmed on 20/09/2017.
 */

@Parcel(analyze = {Movie.class})

public class Movie {
    private final static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185";
    @SerializedName("id")

    public String id; // movie id in API

    @SerializedName("title")
    public String name;// movie title

    @SerializedName("poster_path")
    public String posterPath;//Image URL

    @SerializedName("release_date")
    public String releasedate;// the release date of the movie

    public String overview;// the overview of movie

    @SerializedName("vote_average")
    public String voteAverege; // the voting Avarege of the movie
    // if the movie is add to Favourite

    private boolean isFavourite = false;

    public Movie(String id, String name, String posterPath, String releasedate, String overview, String voteAverege) {
        this.id = id;
        this.name = name;
        this.posterPath = posterPath;
        this.releasedate = releasedate;
        this.overview = overview;
        this.voteAverege = voteAverege;
    }

    public Movie() {
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
        if (posterPath.contains(BASE_IMAGE_URL))
            return posterPath;
        return BASE_IMAGE_URL + posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getVoteAverege() {
        return voteAverege;
    }

    public void setVoteAverege(String voteAverege) {
        this.voteAverege = voteAverege;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
