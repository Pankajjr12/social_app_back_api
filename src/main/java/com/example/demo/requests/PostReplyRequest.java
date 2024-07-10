package com.example.demo.requests;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostReplyRequest {
	
	private String content;
	private Long postId;
	private LocalDateTime createdAt;
	private String image;

}
