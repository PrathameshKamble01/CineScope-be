package com.cinescopefinal.CineScope.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private String description;

    @Column(name = "release_year")
    private int releaseYear;

    @Column(name = "poster_url")
    private String posterUrl;

    @Column(name = "trailor_url")
    private String trailorUrl;

    @Column(name = "movie_url")
    private String movieUrl;

    @Column(name = "create_time")
    private Date createTime;

    @ManyToMany
    @JoinTable(
        name = "movie_movie_type",  // The join table between Movie and MovieType
        joinColumns = @JoinColumn(name = "movie_id"),  // Foreign key for Movie
        inverseJoinColumns = @JoinColumn(name = "movie_type_id")  // Foreign key for MovieType
    )
//    @JsonManagedReference
    private List<MovieType> movieTypes;  // Genres associated with the movie

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getTrailorUrl() {
        return trailorUrl;
    }

    public void setTrailorUrl(String trailorUrl) {
        this.trailorUrl = trailorUrl;
    }

    public String getMovieUrl() {
        return movieUrl;
    }

    public void setMovieUrl(String movieUrl) {
        this.movieUrl = movieUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<MovieType> getMovieTypes() {
        return movieTypes;
    }

    public void setMovieTypes(List<MovieType> movieTypes) {
        this.movieTypes = movieTypes;
    }
}
