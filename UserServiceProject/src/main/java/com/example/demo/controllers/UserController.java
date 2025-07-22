package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // Create User
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount =1;
    
    // Single User Get
    @GetMapping("/{userId}")
  //  @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
  //    @Retry(name = "ratingHotelService", fallbackMethod ="ratingHotelFallback")
        @RateLimiter(name = "UserRateLimiter",  fallbackMethod ="ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
       logger.info("Get Singlt User Handler: UserController");
    	  logger.info("Retry count: {}", retryCount);
          retryCount++;
    	
    	User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

   
    //Creating Fallback Method for CircuitBreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
//        logger.info("Fallback is executed because service is down: {}", ex.getMessage());
        
          User user = new User();
        user.setUserId("000-fallback");
        user.setName("Fallback User");
        user.setEmail("fallback@example.com");
        user.setAbout("This is a fallback user because the service is down.");
        user.setRatings(new ArrayList<>());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // All Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
