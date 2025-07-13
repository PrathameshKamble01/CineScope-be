package com.cinescopefinal.CineScope.repository;

import com.cinescopefinal.CineScope.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

// JpaRepository provides default CRUD operations
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	List<Movie> findByTitleContaining(String title); 

	List<Movie> findByReleaseYear(int releaseYear); 	
	

//	@Query("select * from movie m left join movie_category mc on mc.movie_id  = m.id where mc.category_id in")
//    List<Movie> findByCategoryId(@Param("categoryId") int categoryId);

	@Query("SELECT m FROM Movie m "
	        + "JOIN m.movieTypes mt "
	        + "WHERE mt.id IN :categoryIds")
	List<Movie> findByCategoryId(@Param("categoryIds") List<Integer> categoryIds);

}
