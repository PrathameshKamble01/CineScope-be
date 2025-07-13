package com.cinescopefinal.CineScope.service;

import com.cinescopefinal.CineScope.entities.MovieType;

import java.util.List;
import java.util.Optional;

public interface MovieTypeService {
    List<MovieType> getAllCategories();

    MovieType addCategory(MovieType category);

    void deleteCategory(int id);

    Optional<MovieType> getCategoryById(int id);
}
