package com.huedge.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.huedge.movie.service.MovieService;

@Configuration
@EnableScheduling
public class MovieDataImporter {

    private static final Logger logger = LogManager.getLogger(MovieDataImporter.class);
    @Autowired
    private MovieService movieService;

    /**
     * Scheduler for csv data auto updation
     */
    @Scheduled(cron = "0 * * * * *")
    public void syncDataUpdates() {
        logger.info("Check whether there is new data in csv..");
        movieService.loadCsvFileDataInDB();
    }

}
