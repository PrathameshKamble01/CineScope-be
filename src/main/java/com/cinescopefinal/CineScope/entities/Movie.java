package com.cinescopefinal.CineScope.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {


	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movie_id;

    @Column
    private String title;

    @Column
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

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
        name = "user_movie_types",  // The join table between Movie and genretupe
        joinColumns = @JoinColumn(name = "movie_id"),  // Foreign key for Movie
        inverseJoinColumns = @JoinColumn(name = "genre_id")  // Foreign key for genre
    )
//    @JsonManagedReference
    private List<Genres> movieTypes;  // Genres associated with the movie

    // Getters and Setters


    public List<Genres> getMovieTypes() {
        return movieTypes;
    }

    public void setMovieTypes(List<Genres> movieTypes) {
        this.movieTypes = movieTypes;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
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

}
