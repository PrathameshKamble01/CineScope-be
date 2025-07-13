package com.cinescopefinal.CineScope.service.impl;

import com.cinescopefinal.CineScope.entities.Movie;
import com.cinescopefinal.CineScope.repository.MovieRepository;
import com.cinescopefinal.CineScope.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContaining(title);
    }

    // Get movies by release year
    public List<Movie> getMoviesByReleaseYear(int releaseYear) {
        return movieRepository.findByReleaseYear(releaseYear);
    }

    // Get movies by category
    public List<Movie> getMoviesByCategoryIds(List<Integer> categoryId) {
        return movieRepository.findByCategoryId(categoryId);
    }

    // Add a new movie
    public Movie addMovie(Movie movie) {
        movie.setCreateTime(new java.util.Date()); // Automatically set createTime
        return movieRepository.save(movie);
    }

    public Movie getMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    // Update an existing movie
    public Movie updateMovie(int id, Movie updatedMovie) {
        Movie movie = getMovieById(id);
        if (movie != null) {
            movie.setTitle(updatedMovie.getTitle());
            movie.setDescription(updatedMovie.getDescription());
            movie.setReleaseYear(updatedMovie.getReleaseYear());
            movie.setPosterUrl(updatedMovie.getPosterUrl());
            movie.setTrailorUrl(updatedMovie.getTrailorUrl());
            movie.setMovieUrl(updatedMovie.getMovieUrl());
            return movieRepository.save(movie);
        }
        return null;
    }

    // Delete a movie by ID
    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }
}
