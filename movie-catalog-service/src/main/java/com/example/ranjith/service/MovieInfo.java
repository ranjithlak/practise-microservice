package com.example.ranjith.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.ranjith.model.CatalogItem;
import com.example.ranjith.model.Movie;
import com.example.ranjith.model.Rating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod ="getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie= restTemplate.getForObject("http://movie-info-service/movie/"+rating.getMovieId(),Movie.class);
		return new CatalogItem(movie.getName(),"Test",rating.getRating());
		
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("movie not found","",rating.getRating());
	}

}
