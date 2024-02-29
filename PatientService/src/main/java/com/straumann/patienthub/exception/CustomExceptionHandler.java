package com.straumann.patienthub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex ){ //WebRequest request
		//ErrorResponse errorDetails = ErrorResponse.create(ex.fillInStackTrace(), HttpStatus.NOT_FOUND,  request.getDescription(false));
		//return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> globalExceptionHandler(Exception ex){//WebRequest request
		
		//ErrorResponse errorDetails = ErrorResponse.create(ex.fillInStackTrace(), HttpStatus.INTERNAL_SERVER_ERROR,  request.getDescription(false));
		
		//return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
