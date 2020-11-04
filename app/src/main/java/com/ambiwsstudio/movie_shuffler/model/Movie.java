package com.ambiwsstudio.movie_shuffler.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Movie {

    @ColumnInfo(name = "title")
    @SerializedName("Title")
    @Expose
    private String title;
    @ColumnInfo(name = "year")
    @SerializedName("Year")
    @Expose
    private String year;
    @Ignore
    @SerializedName("Rated")
    @Expose
    private String rated;
    @Ignore
    @SerializedName("Released")
    @Expose
    private String released;
    @ColumnInfo(name = "runtime")
    @SerializedName("Runtime")
    @Expose
    private String runtime;
    @ColumnInfo(name = "genre")
    @SerializedName("Genre")
    @Expose
    private String genre;
    @ColumnInfo(name = "director")
    @SerializedName("Director")
    @Expose
    private String director;
    @Ignore
    @SerializedName("Writer")
    @Expose
    private String writer;
    @ColumnInfo(name = "actors")
    @SerializedName("Actors")
    @Expose
    private String actors;
    @ColumnInfo(name = "plot")
    @SerializedName("Plot")
    @Expose
    private String plot;
    @Ignore
    @SerializedName("Language")
    @Expose
    private String language;
    @Ignore
    @SerializedName("Country")
    @Expose
    private String country;
    @Ignore
    @SerializedName("Awards")
    @Expose
    private String awards;
    @ColumnInfo(name = "poster")
    @SerializedName("Poster")
    @Expose
    private String poster;
    @Ignore
    @SerializedName("Metascore")
    @Expose
    private String metascore;
    @ColumnInfo(name = "imdb_rating")
    @SerializedName("imdbRating")
    @Expose
    private String imdbRating;
    @Ignore
    @SerializedName("imdbVotes")
    @Expose
    private String imdbVotes;
    @Ignore
    @SerializedName("imdbID")
    @Expose
    private String imdbID;
    @Ignore
    @SerializedName("Type")
    @Expose
    private String type;
    @Ignore
    @SerializedName("DVD")
    @Expose
    private String dVD;
    @Ignore
    @SerializedName("BoxOffice")
    @Expose
    private String boxOffice;
    @Ignore
    @SerializedName("Production")
    @Expose
    private String production;
    @Ignore
    @SerializedName("Website")
    @Expose
    private String website;
    @Ignore
    @SerializedName("Response")
    @Expose
    private String response;

    @ColumnInfo(name = "id")
    @PrimaryKey
    private int imdbIdClear;

    @Ignore
    private Bitmap image;

    public void setImdbIdClear(int imdbIdClear) {
        this.imdbIdClear = imdbIdClear;
    }

    public int getImdbIdClear() {
        return imdbIdClear;
    }

    void setImage(Bitmap image) {
        this.image = image;
    }

    String getResponse() {
        return response;
    }

    public String getPoster() {
        return poster;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbID() {
        return imdbID;
    }

    @SuppressWarnings("unused")
    public String getMetascore() {
        return metascore;
    }

    @SuppressWarnings("unused")
    public void setTitle(String title) {
        this.title = title;
    }

    @SuppressWarnings("unused")
    public void setYear(String year) {
        this.year = year;
    }

    @SuppressWarnings("unused")
    public String getRated() {
        return rated;
    }

    @SuppressWarnings("unused")
    public void setRated(String rated) {
        this.rated = rated;
    }

    @SuppressWarnings("unused")
    public String getReleased() {
        return released;
    }

    @SuppressWarnings("unused")
    public void setReleased(String released) {
        this.released = released;
    }

    @SuppressWarnings("unused")
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    @SuppressWarnings("unused")
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @SuppressWarnings("unused")
    public void setDirector(String director) {
        this.director = director;
    }

    @SuppressWarnings("unused")
    public String getWriter() {
        return writer;
    }

    @SuppressWarnings("unused")
    public void setWriter(String writer) {
        this.writer = writer;
    }

    @SuppressWarnings("unused")
    public void setActors(String actors) {
        this.actors = actors;
    }

    @SuppressWarnings("unused")
    public void setPlot(String plot) {
        this.plot = plot;
    }

    @SuppressWarnings("unused")
    public String getLanguage() {
        return language;
    }

    @SuppressWarnings("unused")
    public void setLanguage(String language) {
        this.language = language;
    }

    @SuppressWarnings("unused")
    public String getCountry() {
        return country;
    }

    @SuppressWarnings("unused")
    public void setCountry(String country) {
        this.country = country;
    }

    @SuppressWarnings("unused")
    public String getAwards() {
        return awards;
    }

    @SuppressWarnings("unused")
    public void setAwards(String awards) {
        this.awards = awards;
    }

    @SuppressWarnings("unused")
    public void setPoster(String poster) {
        this.poster = poster;
    }

    @SuppressWarnings("unused")
    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    @SuppressWarnings("unused")
    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    @SuppressWarnings("unused")
    public String getImdbVotes() {
        return imdbVotes;
    }

    @SuppressWarnings("unused")
    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    @SuppressWarnings("unused")
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    @SuppressWarnings("unused")
    public String getType() {
        return type;
    }

    @SuppressWarnings("unused")
    public void setType(String type) {
        this.type = type;
    }

    @SuppressWarnings("unused")
    public String getDVD() {
        return dVD;
    }

    @SuppressWarnings("unused")
    public void setDVD(String dVD) {
        this.dVD = dVD;
    }

    @SuppressWarnings("unused")
    public String getBoxOffice() {
        return boxOffice;
    }

    @SuppressWarnings("unused")
    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    @SuppressWarnings("unused")
    public String getProduction() {
        return production;
    }

    @SuppressWarnings("unused")
    public void setProduction(String production) {
        this.production = production;
    }

    @SuppressWarnings("unused")
    public String getWebsite() {
        return website;
    }

    @SuppressWarnings("unused")
    public void setWebsite(String website) {
        this.website = website;
    }

    @SuppressWarnings("unused")
    public void setResponse(String response) {
        this.response = response;
    }

}
