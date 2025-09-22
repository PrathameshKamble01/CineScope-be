package com.cinescopefinal.CineScope.controller;

import com.cinescopefinal.CineScope.entities.Genres;
import com.cinescopefinal.CineScope.service.MovieTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final MovieTypeService movieTypeService;

    public CategoryController(MovieTypeService movieTypeService) {
        this.movieTypeService = movieTypeService;
    }

    // Get all categories
    @GetMapping("/getMovieTypes")
    public ResponseEntity<List<Genres>> getAllCategories() {
        List<Genres> categories = movieTypeService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // Add a new category
    @PostMapping
    public ResponseEntity<Genres> addCategory(@RequestBody Genres category) {
        Genres createdCategory = movieTypeService.addCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    // Delete a category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id) {
        movieTypeService.deleteCategory(id);
        return ResponseEntity.ok("Category deleted successfully!");
    }
}

