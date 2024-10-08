package com.posts.entity;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.posts.dto.UsersDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@FeignClient(name = "Accounts",configuration = FeignConfig.class)
public interface AccountsFeignClient {
	
		@Retry(name = "Accounts")
		@CircuitBreaker(name = "Accounts",fallbackMethod = "getUsersFallback")
		@GetMapping(path = "/user/{username}")
		public UsersDTO getuser(@PathVariable String username);
		
		default UsersDTO getUsersFallback(String Username,Throwable exceeption){
			System.out.println("FA::LLLLLLBACKKKK" + exceeption.getLocalizedMessage());
			return new UsersDTO("", 0); 
}
}