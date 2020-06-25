package com.prashant.springboot.springbootrestfulwebservices.exception;

import java.util.Date;
import java.util.Iterator;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleALlException(Exception ex, WebRequest request){
		System.out.println("**************** handleAllException ****************");
		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), request.getDescription(false),
				new Date());
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
		System.out.println("**************** UserNotFoundException ****************");
		ExceptionResponse response = new ExceptionResponse(ex.getMessage(), request.getDescription(false), new Date());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, 
			WebRequest request) {

		Iterator<ObjectError> it = ex.getBindingResult().getAllErrors().iterator();
		StringBuilder sb = new StringBuilder();
		while(it.hasNext()) {
			ObjectError error = it.next();
			sb.append(error.getDefaultMessage()).append("\n\t");
		}
		// ExceptionResponse response = new ExceptionResponse("Validation Failed", ex.getBindingResult().toString(), new Date());
		
		ExceptionResponse response = new ExceptionResponse("Validation Failed", sb.toString(), new Date());
		
		// return handleExceptionInternal(ex, HttpStatus.BAD_REQUEST, headers, status, request);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

}
