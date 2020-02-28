package com.example.ranjith.Controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ranjith.Model.Rating;
import com.example.ranjith.Model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @RequestMapping("/movies/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/user/{userId}")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
    	List<Rating> ratings = Arrays.asList(
    			new Rating("1234",4),
    			new Rating("5467",3));
        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;

    }

}
