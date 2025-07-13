package com.cinescopefinal.CineScope.service.impl;

import com.cinescopefinal.CineScope.entities.MovieType;
import com.cinescopefinal.CineScope.repository.MovieRepository;
import com.cinescopefinal.CineScope.repository.MovieTypeRepository;
import com.cinescopefinal.CineScope.service.MovieTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieTypeServiceImpl implements MovieTypeService {

    @Autowired
    private MovieTypeRepository movieTypeRepository; // Inject the repository

    // Get all categories (MovieTypes)
    public List<MovieType> getAllCategories() {
        return movieTypeRepository.findAll();
    }

    // Add a new category (MovieType)
    public MovieType addCategory(MovieType category) {
        return movieTypeRepository.save(category);
    }

    // Delete a category by ID
    public void deleteCategory(int id) {
        Optional<MovieType> category = movieTypeRepository.findById(id);
        if (category.isPresent()) {
            movieTypeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category with ID " + id + " not found");
        }
    }

    // Get a category by ID (optional, in case you need it)
    public Optional<MovieType> getCategoryById(int id) {
        return movieTypeRepository.findById(id);
    }
}
