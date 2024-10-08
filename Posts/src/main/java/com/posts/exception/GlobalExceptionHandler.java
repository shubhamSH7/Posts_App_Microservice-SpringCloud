package com.posts.exception;

import java.util.
HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DeleteExceptions.class)
	public ResponseEntity<String> deletePost(DeleteExceptions ex) {
		String msg = ex.getErrormsg();
		return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UpdateException.class)
	public ResponseEntity<String> notAuthor(UpdateException ex) {
		String msg = ex.getErrormsg();
		return new ResponseEntity<String>(msg, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<String> noResource(NoResourceFoundException ex) {
		return new ResponseEntity<String>("Error 404! resource not found", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UsernameexistsException.class)
	public ResponseEntity<String> userExists(UsernameexistsException ex) {
		String msg = ex.getErrormsg();
		return new ResponseEntity<String>(msg, HttpStatus.CONFLICT);
	}

//	@ExceptionHandler(BadCredentialsException.class)
//	public ResponseEntity<String> badCred() {
//		String msg = "Incorrect Login Details";
//		return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
//	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> valido(MethodArgumentNotValidException ex) {
		Map<String, String> error = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((err) -> {
			String fieldName = ((FieldError) err).getField();
			String message = err.getDefaultMessage();
			error.put(fieldName, message);
		});
		return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);

	}

}
