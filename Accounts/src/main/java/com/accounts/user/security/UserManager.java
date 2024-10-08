package com.accounts.user.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.accounts.user.entity.Account;
import com.accounts.user.entity.Admin;
import com.accounts.user.entity.UserRepository;

@Service
public class UserManager implements UserDetailsService {

	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> users = userRepo.findByUsername(username);
		if (users.get().getUsername().equals("admin")) {
			Admin admin = new Admin(users.get().getUsername(), users.get().getPassword());
			return admin;
		}
		if (users.isEmpty()) {
			throw new UsernameNotFoundException(username + "Not FOund");
		} else
			return users.get();
	}

}
