package com.example.demo.payload;

import org.springframework.http.HttpStatus;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class ApiResponse {
	
    private String message;
    
    private boolean success;
    
    private HttpStatus status;
    
}
