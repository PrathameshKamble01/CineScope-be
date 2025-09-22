package com.cinescopefinal.CineScope.service;

import com.cinescopefinal.CineScope.entities.Genres;

import java.util.List;
import java.util.Optional;

public interface MovieTypeService {
    List<Genres> getAllCategories();

    Genres addCategory(Genres category);

    void deleteCategory(int id);

    Optional<Genres> getCategoryById(int id);
}
