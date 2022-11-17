package com.power.exception;

import java.time.LocalDateTime;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//login exception handler
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> LoginExceptionHandler(LoginException ex, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}
	
	//user exception handler
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorDetails> UserExceptionHandler(UserException ex, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}

	
	//power usage  exception handler
		@ExceptionHandler(PowerUsageException.class)
		public ResponseEntity<MyErrorDetails> PowerUsageExceptionHandler(PowerUsageException ex, WebRequest req) {
			MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		}

	
	
	
	
	//IllegalArgumentException handler

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<MyErrorDetails> IllegalArgumentExceptionHandler(IllegalArgumentException me, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), me.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	
	//RollbackException

	@ExceptionHandler(RollbackException.class)
	public ResponseEntity<MyErrorDetails> handleRollbackException(Exception exp, WebRequest req) {
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),
				"Improper arguments passed in jason. Validation failed", req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

	//ConstraintViolationException
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<MyErrorDetails> handleValidationException(Exception exp, WebRequest req) {
		System.out.println("Inside Constraint Violation Exception. Exception is being handled");
		MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(),
				"Improper arguments passed in json. Validation failed", req.getDescription(false));

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}
	//Exception Handler
	
		@ExceptionHandler(Exception.class)
		public ResponseEntity<MyErrorDetails> myExpHandlerMain(Exception ie, WebRequest wr) {
			System.out.println("inside myHandler method...EXP");
			MyErrorDetails err = new MyErrorDetails(LocalDateTime.now(), ie.getMessage(), wr.getDescription(false));
			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);

		}
	
		
	

	

}
