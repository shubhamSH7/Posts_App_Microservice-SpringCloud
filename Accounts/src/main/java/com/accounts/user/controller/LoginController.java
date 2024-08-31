package com.accounts.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accounts.user.entity.Login;
import com.accounts.user.entity.UserRepository;
import com.accounts.user.security.JWTService;
import com.accounts.user.security.JwtAuthservice;
import com.accounts.user.security.JwtResponse;

import jakarta.validation.Valid;

@RestController
public class LoginController {
	@Autowired
	JWTService jwtService;

	@Autowired
	UserDetailsService uds;

	@Autowired
	JwtAuthservice authserv;

	@Autowired
	UserRepository repo;

	@Autowired
	PasswordEncoder pass;

	@PostMapping(path = "/register")
	public ResponseEntity<JwtResponse> auth(@Valid @RequestBody Login user) {
		return ResponseEntity.ok(authserv.login(user));
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authenticate(@Valid @RequestBody Login user) {
		return ResponseEntity.ok(authserv.authenciate(user));
	}
}
