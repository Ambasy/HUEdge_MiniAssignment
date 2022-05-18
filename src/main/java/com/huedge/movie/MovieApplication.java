package com.huedge.movie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.huedge.filter.MovieAppRequestFilter;
import com.huedge.movie.model.Movie;
import com.huedge.movie.repository.MovieRepository;
import com.huedge.movie.service.MovieService;



@SpringBootApplication
@ComponentScan(basePackages = "com.huedge")
public class MovieApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(MovieApplication.class);

	@Autowired
	private MovieService movieService;
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(MovieApplication.class, args);
		logger.info("{}",context.getBean(MovieAppRequestFilter.class));
	}

	@Override
	public void run(String... args) throws Exception {
		movieService.loadCsvFileDataInDB();	}

}
