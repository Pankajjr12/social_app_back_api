package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.LikeException;
import com.example.demo.exception.PostException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Like;
import com.example.demo.model.User;


public interface LikesService {
	
	public Like likePost(Long postId,User user)throws UserException,PostException;
	
	public Like unlikeTwit(Long twitId, User user) throws UserException, PostException, LikeException;
	
	public List<Like> getAllLikes(Long postId) throws PostException;
	
	
}
