package com.huedge.movie.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huedge.movie.model.Movie;
import com.huedge.movie.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

	private static Logger logger = LoggerFactory.getLogger(MovieController.class);
	
	@Autowired
	private MovieService movieService;

	/**
	 * This method takes director name
	 * and year duration as input
	 * and returns all the movie titles
	 * directed by that director in the year range
	 * 
	 * @param directorName
	 * @param fromYear
	 * @param toYear
	 * @return
	 */
	@GetMapping("/directedBy")
	public ResponseEntity<List<String>> retrieveMoviesByDirectorInDuration(@RequestParam("directorName") String directorName, 
			@RequestParam("fromYear") int fromYear, 
			@RequestParam("toYear") int toYear){
		logger.info("Retrieve all movie titles for given director and made within the duration ..");
		Long startTimeMS = System.currentTimeMillis();
		List<String> titles = movieService.getTitlesListDirectedByForDuration(directorName, fromYear, toYear);
		return new ResponseEntity<>(titles, processResponseHeader(startTimeMS), HttpStatus.OK);
	}
	
	/**
	 * Fetch the movie list
	 * based on the given user reviews
	 * 
	 * @param review
	 * @return
	 */
	@GetMapping("/rating")
	public ResponseEntity<List<Movie>> retrieveMoviesBasedOnReview(@RequestParam("review") int review){
		logger.info("Retrieve all movies having more than given user reviews sorted in descending order..");
		Long startTimeMS = System.currentTimeMillis();
		List<Movie> movies = movieService.getMoviesOrderedByReview(review);
		return new ResponseEntity<>(movies, processResponseHeader(startTimeMS), HttpStatus.OK);
	}
	
	/**
	 * Filter max budget movies
	 * based on year
	 * and country filter
	 * 
	 * @param year
	 * @param country
	 * @return
	 */
	@GetMapping("/MaxBudget")
	public ResponseEntity<List<Movie>> retrieveMaxBudgetMovieForYearAndCountry(@RequestParam("year") int year, 
			@RequestParam("country") String country){
		logger.info("Retrieve all movies having maximum budget for a particular year and given country..");
		Long startTimeMS = System.currentTimeMillis();
		List<Movie> movies = movieService.fetchMaxBudgetMovieForYearAndPlace(year, country);
		return new ResponseEntity<>(movies, processResponseHeader(startTimeMS), HttpStatus.OK);
	}

	/**
	 * Check the time taken for processing each request
	 * and set the value in response header
	 * 
	 * @param startTimeMS
	 * @return
	 */
	private HttpHeaders processResponseHeader(Long startTimeMS) {
		Long timeElapsed = System.currentTimeMillis() - startTimeMS;
		logger.info("Time to execute request = {}", timeElapsed);
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-TIME-TO_EXECUTE", String.valueOf(timeElapsed));
		return headers;
	}
	
}
