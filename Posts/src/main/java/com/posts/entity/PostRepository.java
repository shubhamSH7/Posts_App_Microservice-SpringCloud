package com.posts.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findAllByUserID(int userID);;

	Boolean existsByUserID(int userID);

}
