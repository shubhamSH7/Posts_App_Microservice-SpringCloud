package com.posts.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfig {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	TokenService service;

	@Bean
	RequestInterceptor interceptor() {
		return new RequestInterceptor() {
			
			@Override
			public void apply(RequestTemplate template) {
				String token=service.getJwtToken();
					logger.info(token);
				template.header("Authorization","Bearer "+token);
			}
		};
	}
}
