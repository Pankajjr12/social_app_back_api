package com.example.demo.requests;

import lombok.Data;

@Data
public class LoginWithGooleRequest {
	
	private String credential;
	private String clientId;

}