package com.javaminds.ratingdataservice.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaminds.ratingdataservice.Model.Rating;
import com.javaminds.ratingdataservice.Model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {
	
	private static final int List = 0;

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRatings(@PathVariable("userId") String userId){
		
		List<Rating> ratings =   Arrays.asList(
				new Rating("100",4),
				new Rating("200",3)
				);
		
		UserRating userRating = new UserRating();
		userRating.setRatings(ratings);
		return userRating;
	}

}
