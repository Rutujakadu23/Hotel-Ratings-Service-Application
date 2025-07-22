package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Hotel;

public interface HotelService {
	
	//Create 
	
	Hotel create (Hotel hotel);
	
	
	
	//getall
	
	List<Hotel> getAll();
	
	
	
	//get single
	Hotel get (String id);

}
