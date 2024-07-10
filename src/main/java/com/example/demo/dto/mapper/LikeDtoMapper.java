package com.example.demo.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.LikeDto;
import com.example.demo.dto.PostDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Like;
import com.example.demo.model.User;

public class LikeDtoMapper {
	
	public static LikeDto toLikeDto(Like like, User reqUser) {
		
		UserDto user = UserDtoMapper.toUserDto(like.getUser());
		@SuppressWarnings("unused")
		UserDto reqUserDto = UserDtoMapper.toUserDto(reqUser);
		PostDto post = PostDtoMapper.toPostDto(like.getPost(), reqUser);
		
		
		LikeDto likeDto = new LikeDto();
		likeDto.setId(like.getId());
		likeDto.setPost(post);
		likeDto.setUser(user);
		
		
		return likeDto;
	}

	public static List<LikeDto> toLikeDtos(List<Like> likes,User reqUser){
		
		List<LikeDto> likeDtos = new ArrayList<>();
		
		for(Like like : likes) {
			UserDto user = UserDtoMapper.toUserDto(like.getUser());
			PostDto post = PostDtoMapper.toPostDto(like.getPost(), reqUser);
			
			LikeDto likeDto = new LikeDto();
			likeDto.setId(like.getId());
			likeDto.setPost(post);
			likeDto.setUser(user);
			likeDtos.add(likeDto);
		}
		return likeDtos;
		
	}
}
