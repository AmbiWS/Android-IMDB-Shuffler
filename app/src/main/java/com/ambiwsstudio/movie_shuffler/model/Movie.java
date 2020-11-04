package com.ambiwsstudio.movie_shuffler.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.room.Entity;

@Entity
public class Movie {

    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("Rated")
    @Expose
    private String rated;
    @SerializedName("Released")
    @Expose
    private String released;
    @SerializedName("Runtime")
    @Expose
    private String runtime;
    @SerializedName("Genre")
    @Expose
    private String genre;
    @SerializedName("Director")
    @Expose
    private String director;
    @SerializedName("Writer")
    @Expose
    private String writer;
    @SerializedName("Actors")
    @Expose
    private String actors;
    @SerializedName("Plot")
    @Expose
    private String plot;
    @SerializedName("Language")
    @Expose
    private String language;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Awards")
    @Expose
    private String awards;
    @SerializedName("Poster")
    @Expose
    private String poster;
    @SerializedName("Metascore")
    @Expose
    private String metascore;
    @SerializedName("imdbRating")
    @Expose
    private String imdbRating;
    @SerializedName("imdbVotes")
    @Expose
    private String imdbVotes;
    @SerializedName("imdbID")
    @Expose
    private String imdbID;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("DVD")
    @Expose
    private String dVD;
    @SerializedName("BoxOffice")
    @Expose
    private String boxOffice;
    @SerializedName("Production")
    @Expose
    private String production;
    @SerializedName("Website")
    @Expose
    private String website;
    @SerializedName("Response")
    @Expose
    private String response;

    private String imdbIdClear;

    private Bitmap image;

    void setImdbIdClear(String imdbIdClear) {
        this.imdbIdClear = imdbIdClear;
    }

    String getImdbIdClear() {
        return imdbIdClear;
    }

    void setImage(Bitmap image) {
        this.image = image;
    }

    String getResponse() {
        return response;
    }

    String getPoster() {
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
