package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Rating;

public interface RatingService {
	
	//create
	Rating create (Rating rating);
	
	
	//get all ratings
	List<Rating> getRating();
	
	
	//get all ratings by userId
	List <Rating> getRatingByUserId(String userId);
	
	
	//get all ratings by Hotel 
	List <Rating> getRatingByHotelId(String hotelId);

}
