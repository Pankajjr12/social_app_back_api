package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LikeDto;
import com.example.demo.dto.mapper.LikeDtoMapper;
import com.example.demo.exception.LikeException;
import com.example.demo.exception.PostException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Like;
import com.example.demo.model.User;
import com.example.demo.service.LikesService;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/api")
public class LikeController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LikesService likesService;
	
	
	@PostMapping("/{postId}/like")
	public ResponseEntity<LikeDto> likePost(@PathVariable Long postId,@RequestHeader("Authorization")String jwt)throws UserException,PostException{
		
		User user = userService.findUserProfileByJwt(jwt);
		Like like = likesService.likePost(postId, user);
		
		LikeDto likeDto = LikeDtoMapper.toLikeDto(like, user);
		
		return new ResponseEntity<>(likeDto,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{postId}/unlike")
	public ResponseEntity<LikeDto>unlikeTwit(
			@PathVariable Long postId, 
			@RequestHeader("Authorization") String jwt) throws UserException, PostException, LikeException{
		
		User user=userService.findUserProfileByJwt(jwt);
		Like like =likesService.unlikeTwit(postId, user);
		
		LikeDto likeDto=LikeDtoMapper.toLikeDto(like,user);
		return new ResponseEntity<>(likeDto,HttpStatus.CREATED);
	}
	

	@PostMapping("/post/{postId}")
	public ResponseEntity<List<LikeDto>> getAllLikes(@PathVariable Long postId,@RequestHeader("Authorization")String jwt)throws UserException,PostException{
		
		User user = userService.findUserProfileByJwt(jwt);
		List<Like> likes = likesService.getAllLikes(postId);
		
		List<LikeDto> likeDtos = LikeDtoMapper.toLikeDtos(likes, user);
		
		return new ResponseEntity<>(likeDtos,HttpStatus.CREATED);
	}
	
	
}
