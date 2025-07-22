package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.User;

public interface UserService {
	
	//User Operations
	
	//Create User
	
	User saveUser(User user);
	
	//get All Users
    List<User> getAllUser();
        
    
    //get single user of given userId 
    User getUser(String userId);
        
      //ToDo : Delete
    //  ToDo : Update 
        
}
