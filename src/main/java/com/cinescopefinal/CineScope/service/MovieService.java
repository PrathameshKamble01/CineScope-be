package com.cinescopefinal.CineScope.service;

import com.cinescopefinal.CineScope.entities.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();

    List<Movie> searchMoviesByTitle(String title);

    List<Movie> getMoviesByReleaseYear(int releaseYear);

    List<Movie> getMoviesByCategoryIds(List<Integer> categoryId);

    Movie addMovie(Movie movie);

    Movie getMovieById(int id);

    Movie updateMovie(int id, Movie updatedMovie);

    void deleteMovie(int id);
}
