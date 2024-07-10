package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.LikeException;
import com.example.demo.exception.PostException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Like;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.PostRepository;
@Service
public class LikesServiceImpl implements LikesService {
	
	@Autowired
	private LikeRepository likeRepository;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepository   postRepository;

	@Override
	public Like likePost(Long postId, User user) throws UserException, PostException {
		
		Like isLikeExist = likeRepository.isLikeExists(user.getId(), postId);
		if(isLikeExist != null) {
			likeRepository.deleteById(isLikeExist.getId());
			
			return isLikeExist;
		}
		Post post = postService.findById(postId);
		
		Like like = new Like();
		like.setPost(post);
		like.setUser(user);
		
		Like savedLike = likeRepository.save(like);
		post.getLikes().add(savedLike);
		postRepository.save(post);
		
		return savedLike;
	}

	@Override
	public List<Like> getAllLikes(Long postId) throws PostException {
		
		Post post = postService.findById(postId);
		
		List<Like> likes = likeRepository.findByPostId(post.getId());
		
		return likes;
	}

	@Override
	public Like unlikeTwit(Long postId, User user) throws UserException, PostException, LikeException {
		Like like=likeRepository.findById(postId).orElseThrow(()->new LikeException("Like Not Found"));
		
		if(like.getUser().getId().equals(user.getId())) {
			throw new UserException("somthing went wrong...");
		}
		
		likeRepository.deleteById(like.getId());
		return like;
	}

}
