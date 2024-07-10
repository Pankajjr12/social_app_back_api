package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
	
	@Query("SELECT l FROM Like l WHERE l.user.id=:userId AND l.post.id=:postId")
	public Like isLikeExists(@Param("userId") Long userId, @Param("postId") Long postId);
	
	@Query("SELECT l FROM Like l WHERE l.post.id=:postId")
	public List<Like> findByPostId(@Param("post") Long postId);
}