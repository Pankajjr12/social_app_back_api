package com.example.demo.dto.mapper;

import java.util.ArrayList;
import java.util.List;


import com.example.demo.dto.PostDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.utils.PostUtils;

public class PostDtoMapper {
	
	public static PostDto toPostDto(Post post,User reqUser) {
		
		
		UserDto user = UserDtoMapper.toUserDto(post.getUser());
		boolean isLiked = PostUtils.isLikedByReqUser(reqUser, post);
		boolean isReposted = PostUtils.isRePostByReqUser(reqUser, post);
		
		List<Long> rePostUserId = new ArrayList<>();
		
		for(User user1 : post.getRePostUser()) {
			rePostUserId.add(user1.getId());
		}
		
		PostDto postDto = new PostDto();
		
		postDto.setId(post.getId());
		postDto.setContent(post.getContent());
		postDto.setCreatedAt(post.getCreatedAt());
		postDto.setImage(post.getImage());
		postDto.setTotalLikes(post.getLikes().size());
		postDto.setTotalReplies(post.getReplyPost().size());
		
		postDto.setTotalRePosts(post.getRePostUser().size());
		postDto.setUser(user);
		postDto.setLiked(isLiked);
		postDto.setRePost(isReposted);
		postDto.setRePostUsersId(rePostUserId);
		postDto.setReplyPosts(toPostDtos(post.getReplyPost(), reqUser));
		postDto.setVideo(post.getVideo());
		

		
		return postDto;
		
	}
	


	public static List<PostDto> toPostDtos(List<Post> posts,User reqUser){
		
		List<PostDto> postDtos = new ArrayList<>();
		
		for(Post post: posts) {
			PostDto postDto = toReplyPostDto(post,reqUser);
			postDtos.add(postDto);
				
		}
 		
		return postDtos;
		
	}

	private static PostDto toReplyPostDto(Post post, User reqUser) {
		UserDto user = UserDtoMapper.toUserDto(post.getUser());
		
		boolean isLiked = PostUtils.isLikedByReqUser(reqUser, post);
		boolean isReposted = PostUtils.isRePostByReqUser(reqUser, post);
		
		List<Long> rePostsUserId = new ArrayList<>();
		
		for(User user1 : post.getRePostUser()) {
			rePostsUserId.add(user1.getId());
		}
		
		PostDto postDto = new PostDto();
		
		postDto.setId(post.getId());
		postDto.setContent(post.getContent());
		postDto.setCreatedAt(post.getCreatedAt());
		postDto.setImage(post.getImage());
		postDto.setTotalLikes(post.getLikes().size());
		postDto.setTotalReplies(post.getReplyPost().size());
		
		postDto.setTotalRePosts(post.getRePostUser().size());
		postDto.setUser(user);
		postDto.setLiked(isLiked);
		postDto.setRePost(isReposted);
		postDto.setRePostUsersId(rePostsUserId);
		postDto.setVideo(post.getVideo());
		

	
		return postDto;
		
	}

}
