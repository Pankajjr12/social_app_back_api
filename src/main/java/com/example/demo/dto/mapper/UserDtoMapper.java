package com.example.demo.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.utils.UserUtils;

public class UserDtoMapper {
	
	public static UserDto toUserDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFullName(user.getFullName());
		userDto.setEmail(user.getEmail());
		userDto.setImage(user.getImage());
		userDto.setBackgroundImage(user.getBackgroundImage());
		userDto.setBio(user.getBio());
		userDto.setBirthDate(user.getBirthDate());
		userDto.setFollowers(toUserDtos(user.getFollowers()));
		userDto.setFollowings(toUserDtos(user.getFollowings()));
		userDto.setLogin_with_google(user.isLogin_with_google());
		userDto.setLocation(user.getLocation());
		userDto.setVerified(UserUtils.isVerified(user.getVerification().getEndsAt()));
		
		return userDto;
		
	}

	public static List<UserDto> toUserDtos(List<User> followers) {
		
		List<UserDto> userDtos = new ArrayList<>();
		
		for(User user : followers) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setFullName(user.getFullName());
			userDto.setEmail(user.getEmail());
			userDto.setImage(user.getImage());
			userDtos.add(userDto);
		}
		
 		return userDtos;
	}

}
