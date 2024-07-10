package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Post;
import com.example.demo.model.User;




public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findAllByIsPostTrueOrderByCreatedAtDesc();
	
	@Query("select p from Post p where p.user.id=:userId")
	List<Post> findPostByUserId(Long userId);
	
	List<Post> findByRePostUserContainsOrUser_IdAndIsPostTrueOrderByCreatedAtDesc(User user,Long userId);
	
	List<Post> findByLikesContainingOrderByCreatedAtDesc(User user);
	
	@Query("SELECT p FROM Post p JOIN p.likes l WHERE l.user.id = :userId")
	List<Post> findByLikesUser_Id(Long userId);
	
	
}
