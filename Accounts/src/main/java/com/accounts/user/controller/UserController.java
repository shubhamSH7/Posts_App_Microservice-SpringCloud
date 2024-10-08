package com.accounts.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accounts.user.entity.Account;
import com.accounts.user.entity.UsersDTO;
import com.accounts.user.services.UserService;



@RestController
//@RequestMapping("/admin")
public class UserController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userservice;

	@GetMapping(path = "/users")
	public List<Account> GetAllUsers() {
		return userservice.GetAll();
	}

	@GetMapping(path = "/user/{username}")
	public UsersDTO GetID(@PathVariable String username) {
		return userservice.GetbyUsername(username);
	}

	@PostMapping(path = "/createuser")
	public ResponseEntity<Account> createuser(@RequestBody Account users) {
		logger.info(users.getUsername()+" "+users.getPassword());
		return userservice.CreateUser(users);
	}

	@PutMapping(path = "/update/{username}")
	public ResponseEntity<Account> update(@RequestBody Account users, @PathVariable String username) {
		return userservice.UpdateUser(username, users);
	}

	@DeleteMapping(path = "/delete/{username}")
	public ResponseEntity<String> delete(@PathVariable String username) {
		return userservice.DeleteUser(username);

	}

}
