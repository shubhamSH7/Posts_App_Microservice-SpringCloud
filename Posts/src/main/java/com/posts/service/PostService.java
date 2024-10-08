package com.posts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.posts.dto.PostsDTO;
import com.posts.dto.UsersDTO;
import com.posts.entity.AccountsFeignClient;
import com.posts.entity.Post;
import com.posts.entity.PostRepository;
import com.posts.exception.DeleteExceptions;
import com.posts.exception.PostNotFound;
import com.posts.exception.UpdateException;

@Service
public class PostService{

	@Autowired
	PostRepository postRepo;
	@Autowired
	AccountsFeignClient feignClient;
//	UserRepository userRepo;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	
	//	private Users getCurrentUser() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String username = authentication.getName();
	
//	}
   
	public Post Create(PostsDTO post) {

		Post newpost = new Post(post.getDesc(),post.getUserID());
		return postRepo.save(newpost);
	}

	public List<PostsDTO> getAllPost() {
		List<PostsDTO> pdto = postRepo.findAll().stream()
				.map(m -> new PostsDTO(m.getId(), m.getDesc(), m.getUserID()))
						.collect(Collectors.toList());
		if (pdto.isEmpty()) {
			throw new PostNotFound("No posts available try creating new posts");
		}
		return pdto;
	}

	public List<PostsDTO> getByUserName(String username) {
		UsersDTO user= feignClient.getuser(username);
			logger.info(user.getUsername()+" "+user.getId());
		if (!postRepo.existsByUserID(user.getId())) {
			throw new UsernameNotFoundException(username + "No POST found");
		}
		List<PostsDTO> pdto = postRepo.findAllByUserID(user.getId()).stream()
				.map(m -> new PostsDTO(m.getId(), m.getDesc(), m.getUserID()))
				.collect(Collectors.toList());
		return pdto;
	}

	public List<PostsDTO> getByCurrent(int uid) {
		if (!postRepo.existsByUserID(uid)) {
			throw new PostNotFound("No POST found");
		}
		List<PostsDTO> pdto = postRepo.findAllByUserID(uid).stream()
				.map(m -> new PostsDTO(m.getId(), m.getDesc(), m.getUserID()))
				.collect(Collectors.toList());
		return pdto;
	}

	public PostsDTO update(PostsDTO post, int id,int uid) {

		if (!postRepo.existsById(id)) {
			throw new PostNotFound("No POST found");
		}

		Post p = postRepo.findById(id).get();
		if (uid!=p.getUserID()) {
		throw new UpdateException("You are not the author of this post");
		}
		p.setDesc(post.getDesc());
		postRepo.save(p);
		return post;
	}

	public String delete(int id,int uid) {
		if (!postRepo.existsById(id)) {
			throw new DeleteExceptions("Post Does not exist!");
		}
		Post post = postRepo.findById(id).get();
		if (uid!=post.getUserID()) {
			throw new DeleteExceptions("You are the owner of this post");
		} else {
			postRepo.deleteById(id);
			return "deleted";
		}

	}

}
