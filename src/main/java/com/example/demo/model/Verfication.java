package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Verfication {
	
	private boolean status = false;
	private LocalDateTime startedAt;
	private LocalDateTime endsAt;
	
	private String planType;
	

}
