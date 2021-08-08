package com.javaminds.moviecatalogservice.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.javaminds.moviecatalogservice.modal.CatalogItem;
import com.javaminds.moviecatalogservice.modal.Movie;
import com.javaminds.moviecatalogservice.modal.Rating;
import com.javaminds.moviecatalogservice.modal.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
    // put them all together
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
	
		  
		 
		//get all rated movie ids
		UserRating userRatings = restTemplate.getForObject("http://rating-data-service/ratingsdata/users/"+userId, UserRating.class);
		
		List<Rating> ratings = userRatings.getUserRating();
		//for each movie id, call movie info service and get details
		
//		ratings.stream().map(rating-> {
//			new CatalogItem("Transformers","Test",4)
//		})
//		.collect(Collectors.toList())
		
		
		ArrayList<CatalogItem> l = new ArrayList<CatalogItem>();
		for ( Rating r : ratings ) {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+r.getMovieId(), Movie.class);
			
//			Movie movie = webClientBuilder.build()
//			.get()
//			.uri("http://localhost:8082/movies/"+r.getMovieId())
//			.retrieve()
//			.bodyToMono(Movie.class)
//			.block();
			
			
			l.add(new CatalogItem(movie.getName(),"Test",r.getRating()));
		}
		
		return l; 
		
		
	}
}

