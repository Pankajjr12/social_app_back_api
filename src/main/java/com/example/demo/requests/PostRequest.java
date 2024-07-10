package com.example.demo.requests;

import java.time.LocalDateTime;


import lombok.Data;

@Data
public class PostRequest {
	  
	
	    private String content;
	    
	    private Long postId;

	    private LocalDateTime createdAt;

	    private String image; 
	    
	    private boolean isReply;
	    
	    private boolean isPost;
	    
	    private Long replyFor;
}
