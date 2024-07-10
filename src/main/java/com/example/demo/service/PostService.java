package com.example.demo.service;

import java.util.List;

import com.example.demo.exception.PostException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.requests.PostReplyRequest;

public interface PostService {
	
	public Post createPost(Post post,User user) throws UserException,PostException;
	
	public List<Post> findAllPosts();  
	
	public Post rePost(Long postId,User user)throws UserException, PostException;
	
	public Post findById(Long postId) throws PostException;
	
	public void deletePostById(Long postId,Long userId) throws UserException,PostException;
	
	public Post removeFromRePost(Long postId,User user) throws UserException,PostException;
	
	public Post createReply(PostReplyRequest req,User user)throws PostException;
	
	public List<Post> getUserPost(User user);
	
	public List<Post> findByLikesContainsUser(User user);

	void deletePostAndAssociatedEntities(Long postId, Long id);


	
	
	
	
	
	
	
}
