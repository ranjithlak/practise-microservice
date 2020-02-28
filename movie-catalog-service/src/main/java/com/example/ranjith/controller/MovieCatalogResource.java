package com.example.ranjith.controller;



import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.ranjith.model.CatalogItem;
import com.example.ranjith.model.Movie;
import com.example.ranjith.model.Rating;
import com.example.ranjith.model.UserRating;
import com.example.ranjith.service.MovieInfo;
import com.example.ranjith.service.UserRatingInfo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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
	
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
		
		UserRating ratings=userRatingInfo.getUserRating(userId);
		
	  return ratings.getRatings().stream()
			  .map(rating ->movieInfo.getCatalogItem(rating))
			  .collect(Collectors.toList());
//		  Movie movie =  webClientBuilder.build()
//		                  .get()
//		                  .uri("http://localhost:8080/movie/"+rating.getMovieId())
//		                  .retrieve()
//		                  .bodyToMono(Movie.class)
//		                  .block();
		  
		
			 
	}
	
	
	
	
	
	
	
}
