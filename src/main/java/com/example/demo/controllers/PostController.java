package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PostDto;
import com.example.demo.dto.mapper.PostDtoMapper;
import com.example.demo.exception.PostException;
import com.example.demo.exception.UserException;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.requests.PostReplyRequest;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.PostService;
import com.example.demo.service.UserService;


@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/create")
	public ResponseEntity<PostDto> createPost(@RequestBody Post req, @RequestHeader("Authorization") String jwt) throws UserException,PostException {
		
		 
	        User user = userService.findUserProfileByJwt(jwt);
	        
	        // Create post with the retrieved user
	        Post post = postService.createPost(req, user);
	        
	        // Map post entity to DTO
	        PostDto postDto = PostDtoMapper.toPostDto(post, user);
	        
	        // Return success response
	        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
	    
	        
	}
	
	
	@PostMapping("/reply")
	public ResponseEntity<PostDto> replyPost(@RequestBody PostReplyRequest req,@RequestHeader("Authorization")String jwt)throws UserException,PostException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Post post = postService.createReply(req, user);
		
		PostDto postDto = PostDtoMapper.toPostDto(post, user);
		
		return new ResponseEntity<>(postDto,HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/{postId}/repost")
	public ResponseEntity<PostDto> rePost(@PathVariable Long postId,@RequestHeader("Authorization")String jwt)throws UserException,PostException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Post post = postService.rePost(postId, user);
		
		PostDto postDto = PostDtoMapper.toPostDto(post, user);
		
		return new ResponseEntity<>(postDto,HttpStatus.OK);
		
	}

	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> findPostById(@PathVariable Long postId,@RequestHeader("Authorization")String jwt)throws UserException,PostException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		Post post = postService.findById(postId);
		
		PostDto postDto = PostDtoMapper.toPostDto(post, user);
		
		return new ResponseEntity<>(postDto,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Long postId,@RequestHeader("Authorization")String jwt)throws UserException,PostException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		postService.deletePostById(postId, user.getId());
		
		ApiResponse res = new ApiResponse();
		res.setMessaage("Post deleted successfully");
		res.setStatus(true);
		
		return new ResponseEntity<>(res,HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<PostDto>> getAllPosts(@RequestHeader("Authorization")String jwt)throws UserException,PostException {
		
		User user = userService.findUserProfileByJwt(jwt);
		
		List<Post> posts = postService.findAllPosts();
		
		List<PostDto> postDtos = PostDtoMapper.toPostDtos(posts, user);
		
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getUserAllPosts(@PathVariable Long userId,@RequestHeader("Authorization")String jwt)throws UserException,PostException {
		
		User reqUser = userService.findUserProfileByJwt(jwt);
		User user=userService.findUserById(userId);
		
		List<Post> posts = postService.getUserPost(user);
				
		List<PostDto> postDtos = PostDtoMapper.toPostDtos(posts, reqUser);
		
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/user/{userId}/likes")
	public ResponseEntity<List<PostDto>> findPostBylikesContainedUser(@PathVariable Long userId,@RequestHeader("Authorization")String jwt)throws UserException,PostException {
		
		User reqUser=userService.findUserProfileByJwt(jwt);
		User user=userService.findUserById(userId);
		
		List<Post> posts = postService.findByLikesContainsUser(user);
				
		List<PostDto> postDtos = PostDtoMapper.toPostDtos(posts, reqUser);
		
		return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.ACCEPTED);
		
	}

}
