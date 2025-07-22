package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
		
		String message = ex.getMessage();	
		ApiResponse response = ApiResponse.builder()
			    .message("Lombok is working!")
			    .success(true)
			    .status(HttpStatus.OK)
			    .build();
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		
		
	}
	

}
