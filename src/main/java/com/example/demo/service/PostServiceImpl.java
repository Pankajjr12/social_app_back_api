package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.PostException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.requests.PostReplyRequest;


@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepository postRepository;
	

	
	@Override
	public Post createPost(Post req, User user)  {
		 
	

        // Create the post
        Post post = new Post();
        post.setContent(req.getContent());
     
        post.setCreatedAt(LocalDateTime.now());
        post.setImage(req.getImage());
        post.setUser(user); // Use the existing user
        post.setReply(false);
        post.setPost(true);
        post.setVideo(req.getVideo());
        postRepository.save(post);
		
		return post;
    }
	@Override
	public List<Post> findAllPosts() {
		
		return postRepository.findAllByIsPostTrueOrderByCreatedAtDesc();
	}

	@Override
	public Post rePost(Long postId, User user) throws  PostException {
		
		Post post = findById(postId);
		if(post.getRePostUser().contains(user)) {
			post.getRePostUser().remove(user);
		}
		else {
			post.getRePostUser().add(user);
		}
		return postRepository.save(post);
	}

	@Override
	public Post findById(Long postId) throws PostException {
		
		Post post = postRepository.findById(postId)
		.orElseThrow(()-> new PostException("Post not found with id "+postId));
		return post;
	}

	@Override
	public void deletePostById(Long postId, Long userId) throws UserException, PostException {
		Post post = findById(postId);
		if(!userId.equals(post.getUser().getId())) {
			throw new UserException("You can't delete another user post");
			
		}
		
		postRepository.deleteById(post.getId());
		
	}


	@Override
	public Post createReply(PostReplyRequest req, User user) throws PostException {
		
		Post post = findById(req.getPostId());
		
		Post reply = new Post();
		reply.setContent(req.getContent());
		reply.setCreatedAt(LocalDateTime.now());
		reply.setImage(req.getImage());
		reply.setUser(user);
		reply.setReplyFor(post);
		reply.setReply(true);
		reply.setPost(false);
		
		
		Post savedreply = postRepository.save(post);
		
		post.getReplyPost().add(savedreply);
		
		postRepository.save(post);
		
		return post;
	}

	@Override
	public List<Post> getUserPost(User user) {
		return postRepository.findByRePostUserContainsOrUser_IdAndIsPostTrueOrderByCreatedAtDesc(user, user.getId());
	}

	@Override
	public List<Post> findByLikesContainsUser(User user) {
		
		return postRepository.findByLikesUser_Id(user.getId());
	}

	@Override
	public Post removeFromRePost(Long postId, User user) throws UserException, PostException {
		
		Post post = findById(postId);
		post.getRePostUser().remove(user);
		
		return postRepository.save(post);
	}
	

	@Override
	public void deletePostAndAssociatedEntities(Long postId, Long id) {
		
		
	}


}
