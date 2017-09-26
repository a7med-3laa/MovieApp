package com.ahmedalaa.movieapp.data;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.provider.ContentUri;
import com.raizlabs.android.dbflow.annotation.provider.TableEndpoint;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.parceler.Parcel;
import org.parceler.Transient;

import java.util.List;

/**
 * Created by ahmed on 20/09/2017.
 */

@Table(database = MyDatabase.class)
@Parcel(analyze = {Movie.class})
@TableEndpoint(name = Movie.ENDPOINT, contentProvider = MyDatabase.class)

public class Movie extends BaseModel {
    public final static String ENDPOINT = "movies";
    @ContentUri(path = Movie.ENDPOINT,
            type = ContentUri.ContentType.VND_MULTIPLE + ENDPOINT)
    public static final Uri CONTENT_URI = MyDatabase.buildUri(ENDPOINT);
    private final static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185";
    @SerializedName("id")
    @Column
    @PrimaryKey()
    public String id; // movie id in API
    @Column
    @SerializedName("title")
    public String name;// movie title
    @Column
    @SerializedName("poster_path")
    public String posterPath;//Image URL
    @Column
    @SerializedName("release_date")
    public String releasedate;// the release date of the movie
    @Column
    public String overview;// the overview of movie
    @Column
    @SerializedName("vote_average")
    public String voteAverege; // the voting Avarege of the movie
    // if the movie is add to Favourite
    @Column
    public boolean favourite = false;
    @Column
    @SerializedName("backdrop_path")
    public String backdrop_bath;
    @Transient
    public List<Trailer> trailers;
    @Transient
    public List<Review> reviews;

    public Movie(String name, String posterPath, String releasedate, String overview, String voteAverege, Boolean favourite, String id, String backdrop_bath) {
        this.name = name;
        this.posterPath = posterPath;
        this.releasedate = releasedate;
        this.overview = overview;
        this.voteAverege = voteAverege;
        this.favourite = favourite;
        this.id = id;
        this.backdrop_bath = backdrop_bath;
    }

    public Movie() {
    }

    @OneToMany(methods = OneToMany.Method.ALL, variableName = "trailers")
    public List<Trailer> getTrailersforMovie() {
        if (trailers == null) {
            trailers = SQLite.select()
                    .from(Trailer.class)
                    .where(Trailer_Table.Movie_id.eq(id))
                    .queryList();
        }
        return trailers;
    }

    @OneToMany(methods = OneToMany.Method.ALL, variableName = "reviews")
    public List<Review> getReviewsforMovie() {
        if (reviews == null) {
            reviews = SQLite.select()
                    .from(Review.class)
                    .where(Review_Table.Movie_id.eq(id))
                    .queryList();
        }
        return reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosterPath() {
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

    public boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBackdrop_bath() {
        return backdrop_bath;
    }

    public void setBackdrop_bath(String backdrop_bath) {
        this.backdrop_bath = backdrop_bath;
    }

}
