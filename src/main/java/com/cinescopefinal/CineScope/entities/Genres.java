package com.cinescopefinal.CineScope.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "genres")
public class Genres {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreId;

    @Column
    private String type;  // Genre like Action, Sci-Fi, etc.

    @ManyToMany(mappedBy = "movieTypes")
//    @JsonBackReference
    @JsonIgnore
    private List<Movie> movies;  // Movies associated with this type

    // Getters and Setters


    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
