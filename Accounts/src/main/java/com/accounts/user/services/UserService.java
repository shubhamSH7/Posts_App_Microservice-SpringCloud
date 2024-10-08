package com.accounts.user.services;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.accounts.user.entity.Account;
import com.accounts.user.entity.UserRepository;
import com.accounts.user.entity.UsersDTO;


@Service
public class UserService {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	PasswordEncoder passwordEncoder;
	UserRepository userRepo;

	public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		super();
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public List<Account> GetAll() {
		return userRepo.findAll();
	}

	public UsersDTO GetbyUsername(String username) {
		
			Account acc=userRepo.findByUsername(username).get();
			UsersDTO dto=new UsersDTO(acc.getUsername(),acc.getId());
			logger.info(acc.getUsername()+" "+acc.getId());
			logger.info(dto.getUsername()+" "+dto.getId());
			return dto;
	}

	public ResponseEntity<Account> CreateUser(Account users) {
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		userRepo.save(users);
		return new ResponseEntity<Account>(HttpStatus.OK);
	}

	public ResponseEntity<Account> UpdateUser(String username, Account users) {
		Optional<Account> extuser = userRepo.findByUsername(username);
		if (extuser.isPresent()) {
			extuser.get().setUsername(users.getUsername());
			extuser.get().setPassword(passwordEncoder.encode(users.getPassword()));			
			userRepo.save(extuser.get());
			return new ResponseEntity<Account>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> DeleteUser(String username) {
		if (userRepo.existsByUsername(username)) {
			int id = userRepo.findByUsername(username).get().getId();
			userRepo.deleteById(id);

			return ResponseEntity.ok("Deleted");
		}
		return ResponseEntity.ok("NO User OF ID " + " FOUND");
	}

}
