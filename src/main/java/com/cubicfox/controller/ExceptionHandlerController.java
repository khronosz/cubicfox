package com.cubicfox.controller;

import java.util.Iterator;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cubicfox.exception.BadRequestException;
import com.cubicfox.exception.ErrorMessage;
import com.cubicfox.exception.IncorrectUserResponseException;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class ExceptionHandlerController {

	@ExceptionHandler(IncorrectUserResponseException.class)
	public ResponseEntity<ErrorMessage> incorrectUserResponseExceptionHandler(IncorrectUserResponseException e) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_ACCEPTABLE.value(), HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), e.getMessage());
		log.error(message.getStatusCode() + ": " + message.getStatus() + " - " + message.getMessage());
		return new ResponseEntity<>(message, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorMessage> badResponseExceptionHandler(BadRequestException e) {
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
		log.error(message.getStatusCode() + ": " + message.getStatus() + " - " + message.getMessage());
		return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> constraintViolationExceptionHandler(ConstraintViolationException e) {
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
		Iterator violations = e.getConstraintViolations().iterator();
		if (violations.hasNext()) {
			ConstraintViolationImpl violation = (ConstraintViolationImpl) violations.next();
			message.setMessage(violation.getMessageTemplate());
			log.error(message.getStatusCode() + ": " + message.getStatus() + " - " + message.getMessage());
			return new ResponseEntity<>(message.getMessage(), HttpStatus.BAD_REQUEST);
		}
		message.setMessage("Unknown Validation Constraint Violation Error!");
		log.error(message.getStatusCode() + ": " + message.getStatus() + " - " + message.getMessage());
		return new ResponseEntity<>(message.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
