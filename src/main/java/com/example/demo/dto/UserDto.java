package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	
	private Long id;
	private String fullName;
	private String email;
	private String image;
	private String location;
	private String website;
	private String birthDate;
	private String bio;
	private String mobile;
	private String backgroundImage;
	private boolean reqUser;
	
	private boolean login_with_google;
	
	private List<UserDto> followers = new ArrayList<>();
	private List<UserDto> followings = new ArrayList<>();
	
	private boolean followed;
	
	private boolean isVerified;
	
	
}
