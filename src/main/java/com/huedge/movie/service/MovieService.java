package com.huedge.movie.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huedge.movie.MovieApplication;
import com.huedge.movie.exception.DataNotFoundException;
import com.huedge.movie.exception.InvalidInputException;
import com.huedge.movie.exception.ResourceNotFoundException;
import com.huedge.movie.model.Movie;
import com.huedge.movie.repository.MovieRepository;

@Service
public class MovieService {

	private static Logger logger = LoggerFactory.getLogger(MovieService.class);

	@Autowired
	MovieRepository movieRepository;	
	
	public List<String> getTitlesListDirectedByForDuration (String directorName, int fromYear, int toYear) throws InvalidInputException, DataNotFoundException{
		if(Objects.isNull(directorName)) {
			logger.error("Please enter a valid input..");
			throw new InvalidInputException("Please check the Input");
		}
		Optional<List<String>> titleList = movieRepository.searchDirectedByForRange(directorName, fromYear, toYear);
        if(!titleList.isPresent()) {
            logger.error("No Data Found");
            throw new DataNotFoundException("No Data Found For Entered Criteria!");
        }
        return titleList.get();
	}
	
	public List<Movie> getMoviesOrderedByReview (int review) throws DataNotFoundException {
		Optional<List<Movie>> movieList = movieRepository.sortByReview(review);
		if(!movieList.isPresent()) {
	        logger.error("No Data Found");
	        throw new DataNotFoundException("No Data Found For Entered Criteria!");
		}
		return movieList.get();
	}
	
	public List<Movie> fetchMaxBudgetMovieForYearAndPlace(int year, String country) throws InvalidInputException, DataNotFoundException{
		if(Objects.isNull(country)) {
			logger.error("Please enter a valid input..");
			throw new InvalidInputException("Please check the Input");
		}
		Optional<List<Movie>> movieList = movieRepository.getMaxBudgetMovieForYearAndCountry(year, country);
		if(!movieList.isPresent()) {
	        logger.error("No Data Found");
	        throw new DataNotFoundException("No Data Found For Entered Criteria!");
		}
		return movieList.get();
	}
	
	/**
	 * CSV file loader
	 */
	public void loadCsvFileDataInDB() {
		List<Movie> movieList = new ArrayList<>();
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(MovieApplication.class.getResourceAsStream("/csv/movies.csv")))) {
			String line = bufferedReader.readLine();
			while((line = bufferedReader.readLine()) != null) {
				Movie movie = parseCsvLine(line);
				movieList.add(movie);
			}
			bufferedReader.close();
			movieRepository.saveAll(movieList);
		} catch(IOException ex) {
			logger.error("Unable to process Csv file");
			throw new ResourceNotFoundException("CSV file not imported correctly!");
		}
	}

	/***
	 * Method to parse each Csv line
	 * 
	 * @param line
	 * @return
	 */
	private static Movie parseCsvLine(String line) {
		/**
		 * Regex to process the CSV file 
		 * as each column can also have multiple comma separated values
		 */
		Pattern pattern = Pattern.compile(",(?=([^\"]*\"[^\"]*\")*(?![^\"]*\"))");
        // Splitting input with the pattern
        String[] fields = pattern.split(line);
        for (int i = 0; i < fields.length; i++) {
            // Removing the remaining double quotes
            fields[i] = fields[i].replace("\"", "");
        }
		Movie movie = populateCsvDataInModel(fields);
		return movie;
	}

	/**
	 * Method populate
	 * the values of
	 * each movie in the model
	 * 
	 * @param fields
	 * @return
	 */
	private static Movie populateCsvDataInModel(String[] fields) {
		Movie movie = new Movie();
		movie.setImdbTitleId(fields[0]);
		movie.setTitle(fields[1]);
		movie.setOriginalTitle(fields[2]);
		movie.setYear(StringUtils.isNotBlank(fields[3]) ? Integer.parseInt(fields[3]) : null);
		movie.setDate_published(fields[4]);
		movie.setGenre(fields[5]);
		movie.setDuration(StringUtils.isNotBlank(fields[6]) ? Integer.parseInt(fields[6]) : null);
		movie.setCountry(fields[7]);
		movie.setLanguage(fields[8]);
		movie.setDirector(fields[9]);
		movie.setWriter(fields[10]);
		movie.setProductionCompany(fields[11]);
		movie.setActors(fields[12]);
		movie.setDescription(fields[13]);
		movie.setAvg_vote(fields[14]);
		movie.setVotes(StringUtils.isNotBlank(fields[15]) ? Integer.parseInt(fields[15]) : null);
		movie.setReviews_from_users((int)Math.floor(Math.random()*(100-4+1)+4));
		movie.setReviews_from_critics((int)Math.floor(Math.random()*(100-4+1)+4));
		movie.setBudget(fields.length > 17 ? fields[16] : null);
		movie.setUsa_gross_income(fields.length > 18 ? fields[17] : null);
		movie.setWorlwide_gross_income(fields.length > 19 ?fields[18] : null);
		return movie;
	}

}
