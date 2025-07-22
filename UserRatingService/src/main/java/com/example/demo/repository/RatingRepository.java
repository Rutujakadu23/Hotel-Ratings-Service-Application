package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entities.Rating;


public interface RatingRepository extends MongoRepository<Rating, String> {

	//Custome Find methods 
	
	List<Rating> findByUserId(String userId);
	
	List<Rating> findByHotelId(String hotelId);
	
	
	
	
	
	
}
