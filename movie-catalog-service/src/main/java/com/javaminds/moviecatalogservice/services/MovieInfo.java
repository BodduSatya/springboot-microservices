package com.javaminds.moviecatalogservice.services;

import com.javaminds.moviecatalogservice.modal.CatalogItem;
import com.javaminds.moviecatalogservice.modal.Movie;
import com.javaminds.moviecatalogservice.modal.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieInfo {

    @Autowired
    private RestTemplate restTemplate;

    /*
       Bulkhead pattern ( create separate pools for each services )
       @HystrixCommand(
           fallbackMethod = "getFallbackCatalogItem",
           threadPoolKey="movieInfoPool",
           threadPoolProperties={
              @HystrixProperty(name="coreSize", value="20"),
              @HystrixProperty(name="maxQueueSize", value="10")
           }
       )
    */
    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);
        return new CatalogItem(movie.getName(),movie.getDescription(), rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie name not found","", rating.getRating());
    }

}
