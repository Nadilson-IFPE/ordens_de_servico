package com.nadilson.os.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nadilson.os.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExcpetionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e) {
		StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				e.getMessage());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
	}

}
