package com.huedge.movie.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.huedge.movie.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	@Query(nativeQuery = true, value = "SELECT title FROM movie m WHERE m.director=:director AND m.year BETWEEN :fromYear AND :toYear")
	public Optional<List<String>> searchDirectedByForRange(@Param("director") String director, @Param("fromYear") int fromYear,
			@Param("toYear") int toYear);
	
	@Query(nativeQuery = true, value = "SELECT * FROM movie m WHERE m.reviews_from_users>:review ORDER BY m.reviews_from_users DESC")
	public Optional<List<Movie>> sortByReview(@Param("review") int review);
	
	@Query(nativeQuery = true, value = "SELECT * FROM movie m WHERE m.budget = (SELECT max(budget) from movie where year=:year AND country=:country)")
	public Optional<List<Movie>> getMaxBudgetMovieForYearAndCountry(@Param("year") int year, @Param("country") String country);
	
}