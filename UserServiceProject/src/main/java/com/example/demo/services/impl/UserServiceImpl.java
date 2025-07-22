package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entities.Hotel;
import com.example.demo.entities.Rating;
import com.example.demo.entities.User;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.external.services.HotelService;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		//Generate Unique UserId 
		String randomUserId  = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		//implements RATINGS SERVICE CALL : USING REST TEMPLATE
		return userRepository.findAll();
	}

	// get single user
	
	@Override
	public User getUser(String userId) {
	    // Get user from DB
	    User user = userRepository.findById(userId)
	        .orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! " + userId));

	    // Call Rating Service and get ratings for the user
	    Rating[] ratingArray = restTemplate.getForObject(
	        "http://USERRATINGSERVICE/ratings/users/" + userId,
	        Rating[].class
	    );

	    logger.info("Ratings fetched from Rating-Service: {}", (Object) ratingArray);

	    // Convert array to List and fetch hotel details
	    List<Rating> ratingList = Arrays.stream(ratingArray).map(rating -> {
	        // API call to Hotel Service to get the hotel
	     //   ResponseEntity<Hotel> hotelResponse = restTemplate.getForEntity(
	     //       "http://USERHOTELSERVICE/hotels/" + rating.getHotelId(),
	      //      Hotel.class
	   //     );

	      //  Hotel hotel = hotelResponse.getBody();
	        Hotel hotel = hotelService.getHotel(rating.getHotelId());

	     //   logger.info("Hotel response status code: {}", hotelResponse.getStatusCode());

	        // Set hotel into rating
	        rating.setHotel(hotel);
	        return rating;
	    }).collect(Collectors.toList());

	    // Set ratings into user
	    user.setRatings(ratingList);

	    return user;
	}
}