package com.example.demo.utils;

import com.example.demo.model.Like;
import com.example.demo.model.Post;
import com.example.demo.model.User;

public class PostUtils {


	public final static  boolean isLikedByReqUser(User reqUser, Post post) {
		
		for(Like like: post.getLikes()) {
			if(like.getUser().getId().equals(reqUser.getId())) {
				return true;
			}
		}
		return false;
		
	}
	

	public final static  boolean isRePostByReqUser(User reqUser, Post post) {
		for(User user: post.getRePostUser()) {
			if(user.getId().equals(reqUser.getId())) {
				return true;
			}
		}
		return false;
	}
}
