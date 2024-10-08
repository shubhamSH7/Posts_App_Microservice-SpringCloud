package com.posts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.posts.dto.PostsDTO;
import com.posts.dto.UsersDTO;
import com.posts.security.JWTService;
import com.posts.service.PostService;

import jakarta.validation.Valid;

@RestController
public class PostController {

	@Autowired
	PostService postservice;

	@Autowired
	JWTService jwtService;

	public UsersDTO getUserDet(@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);
		UsersDTO user = new UsersDTO(jwtService.extractUsername(token), jwtService.extractUID(token));
		return user;
	}

	@GetMapping(path = "/allposts")
	public ResponseEntity<List<PostsDTO>> getPosts() {
		List<PostsDTO> allposts = postservice.getAllPost();
		return new ResponseEntity<List<PostsDTO>>(allposts, HttpStatus.ACCEPTED);
	}

	@PostMapping(path = "/newpost")
	public ResponseEntity<PostsDTO> createpost(@RequestHeader("Authorization") String authHeader,
			@Valid @RequestBody PostsDTO npost) {
		String token = authHeader.substring(7);
		int id = jwtService.extractUID(token);
		npost.setUserID(id);
		postservice.Create(npost);
		return new ResponseEntity<PostsDTO>(npost, HttpStatus.CREATED);

	}

	@GetMapping(path = "/byuser")
	public ResponseEntity<List<PostsDTO>> byuser(@RequestHeader("Authorization") String authHeader) {
		String token = authHeader.substring(7);
		int UID = jwtService.extractUID(token);
		List<PostsDTO> byCurrent = postservice.getByCurrent(UID);
		return new ResponseEntity<List<PostsDTO>>(byCurrent, HttpStatus.FOUND);
	}

	@GetMapping(path = "/user/{username}")
	public ResponseEntity<List<PostsDTO>> allposts(@PathVariable String username) {

		List<PostsDTO> byCurrent = postservice.getByUserName(username);
		return new ResponseEntity<List<PostsDTO>>(byCurrent, HttpStatus.FOUND);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> del(@RequestHeader("Authorization") String authHeader, @PathVariable int id) {
		String token = authHeader.substring(7);
		int UID = jwtService.extractUID(token);

		postservice.delete(id, UID);
		return new ResponseEntity<String>("deleted", HttpStatus.ACCEPTED);
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<PostsDTO> updatePost(@RequestHeader("Authorization") String authHeader,
			@Valid @RequestBody PostsDTO updatePost, @PathVariable int id) {
		String token = authHeader.substring(7);
		int UID = jwtService.extractUID(token);
		postservice.update(updatePost, id, UID);
		return new ResponseEntity<PostsDTO>(updatePost, HttpStatus.ACCEPTED);
	}
}
