package com.cinescopefinal.CineScope.controller;

import com.cinescopefinal.CineScope.entities.Movie;
import com.cinescopefinal.CineScope.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/allMovies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping("/moviesByTypes")
    public ResponseEntity<List<Movie>> getMoviesByTypes(@RequestBody Map<String, List<Integer>> requestBody) {
        List<Integer> movieTypeIds = requestBody.get("movieTypeIds");
        if (movieTypeIds == null || movieTypeIds.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<Movie> movies = movieService.getMoviesByCategoryIds(movieTypeIds);

        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no movies found
        }

        return ResponseEntity.ok(movies);
    }

    // Get movie by ID
    @GetMapping("/{id}")
    public Optional<Movie> getMovieById(@PathVariable int id) {
        return Optional.ofNullable(movieService.getMovieById(id));
    }

    // Search movies by title
    @GetMapping("/search")
    public List<Movie> searchMoviesByTitle(@RequestParam String title) {
        return movieService.searchMoviesByTitle(title);
    }

    // Get movies by release year
    @GetMapping("/year/{releaseYear}")
    public List<Movie> getMoviesByReleaseYear(@PathVariable int releaseYear) {
        return movieService.getMoviesByReleaseYear(releaseYear);
    }
    // Filter movies by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Movie>> getMoviesByCategory(@PathVariable Integer categoryId) {
        List<Movie> movies = movieService.getMoviesByCategoryIds(Arrays.asList(categoryId));
        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 No Content if no movies found
        }

        return ResponseEntity.ok(movies);
    }

    // Add a new movie
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie savedMovie = movieService.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovie);
    }

    // Update an existing movie
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie) {
        return movieService.updateMovie(id, movie);
    }

    // Delete a movie by ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted successfully!");
    }
}
