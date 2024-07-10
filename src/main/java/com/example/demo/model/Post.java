package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


import lombok.Data;


@Entity
@Data
public class Post {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(nullable = false)
	private String content;

	//Many post have one user
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private List<Like> likes = new ArrayList<>(); 
	
	@OneToMany
	private List<Post> replyPost = new ArrayList<>();
	
	@ManyToMany
	private List<User> rePostUser = new ArrayList<>();
	
	
	@ManyToOne
	@JoinColumn(name = "re_posts_id", nullable = true)
	private Post replyFor;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	 
	private String image;
	private String video;
	
    private boolean isReply;
	private boolean isPost; 
	
    private boolean is_liked = false;
    private boolean is_repost = false;
   


}
