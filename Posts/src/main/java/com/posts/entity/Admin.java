//package com.posts.entity;
//
//import java.util.Collection;
//import java.util.Collections;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class Admin implements UserDetails {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	String username;
//	String password;
//
//	public Admin(String username, String password) {
//		super();
//		this.username = username;
//		this.password = password;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return Collections.singletonList(new SimpleGrantedAuthority("admin"));
//	}
//
//	@Override
//	public String getPassword() {
//		return password;
//	}
//
//	@Override
//	public String getUsername() {
//		return username;
//	}
//
//}
