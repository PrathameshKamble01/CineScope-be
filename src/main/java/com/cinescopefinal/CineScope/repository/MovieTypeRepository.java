package com.cinescopefinal.CineScope.repository;

import com.cinescopefinal.CineScope.entities.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTypeRepository extends JpaRepository<Genres, Integer> {
    
}
