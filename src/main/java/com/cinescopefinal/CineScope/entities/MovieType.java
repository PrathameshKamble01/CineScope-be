package com.cinescopefinal.CineScope.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "movie_type")
public class MovieType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String type;  // Genre like Action, Sci-Fi, etc.

    @ManyToMany(mappedBy = "movieTypes")
//    @JsonBackReference
    @JsonIgnore
    private List<Movie> movies;  // Movies associated with this type

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
