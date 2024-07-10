package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.mapper.UserDtoMapper;
import com.example.demo.exception.PostException;
import com.example.demo.exception.UserException;
import com.example.demo.model.User;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.UserService;
import com.example.demo.utils.UserUtils;


@RestController
@RequestMapping("/api/users")

public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization")String jwt)throws UserException,PostException{
		
		User user = userService.findUserProfileByJwt(jwt);
		user.setPassword(null);
		user.setReq_user(true);
		UserDto userDto = UserDtoMapper.toUserDto(user);
		userDto.setReqUser(true);
		
		
		return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Long userId,@RequestHeader("Authorization")String jwt)throws UserException,PostException{
		
		User reqUser = userService.findUserProfileByJwt(jwt);
		User user = userService.findUserById(userId);
		UserDto userDto = UserDtoMapper.toUserDto(user);
		userDto.setReqUser(UserUtils.isReqUser(reqUser, user));
		userDto.setFollowed(UserUtils.isFollowedByReqUser(reqUser, user));
		
		
		return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<UserDto>> searchUser(@RequestParam String query,@RequestHeader("Authorization")String jwt)throws UserException,PostException{
		
		@SuppressWarnings("unused")
		User reqUser = userService.findUserProfileByJwt(jwt);
		List<User> users = userService.searchUser(query);
		
		List<UserDto> userDtos = UserDtoMapper.toUserDtos(users);
		
		return new ResponseEntity<>(userDtos,HttpStatus.ACCEPTED);
		
	}

	@PutMapping("/{userId}/follow")
	public ResponseEntity<UserDto> followUser(@PathVariable Long userId,@RequestHeader("Authorization")String jwt)throws UserException,PostException{
		User reqUser = userService.findUserProfileByJwt(jwt);
		User user = userService.followUser(userId, reqUser);
		
		UserDto userDto = UserDtoMapper.toUserDto(user);
		userDto.setFollowed(UserUtils.isFollowedByReqUser(reqUser, user));
		
		return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserDto> updateUser(@RequestBody User req,@RequestHeader("Authorization")String jwt)throws UserException,PostException{
		
		User reqUser = userService.findUserProfileByJwt(jwt);
		User updatedUser = userService.updateUser(reqUser.getId(),req);
		updatedUser.setPassword(null);
		UserDto userDto = UserDtoMapper.toUserDto(updatedUser);
		updatedUser.setPassword(null);
		
		return new ResponseEntity<>(userDto,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Long userId, @RequestHeader("Authorization") String jwt) throws UserException {
        userService.deleteUserById(userId);

        ApiResponse res = new ApiResponse();
        res.setMessaage("User deleted successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

	

	@GetMapping
	    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String jwt) {
	        List<User> users = userService.getAllUsers();
	        return ResponseEntity.ok(users);
	    }
	
	
}
