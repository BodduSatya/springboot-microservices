package com.javaminds.moviecatalogservice.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.javaminds.moviecatalogservice.services.MovieInfo;
import com.javaminds.moviecatalogservice.services.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

	@Autowired
	MovieInfo movieInfo;

	@Autowired
	UserRatingInfo userRatingInfo;

	// put them all together
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		//get all rated movie ids
		UserRating userRatings = userRatingInfo.getUserRating(userId);

		return userRatings.getRatings().stream()
				.map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());
	}

	/*Movie movie = webClientBuilder.build()
	.get()
	.uri("http://localhost:8082/movies/"+r.getMovieId())
	.retrieve()
	.bodyToMono(Movie.class)
	.block();*/
}

