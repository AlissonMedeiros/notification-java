package org.medeiros.adapter.controller.v1.handler;

import org.medeiros.usecase.exception.MessageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(MessageNotFoundException.class)
	public ResponseEntity<Object> handleMessageNotFoundException(
		MessageNotFoundException ex,
		WebRequest request
	) {
		ErrorDto error = ErrorDto.builder()
			.message(ex.getMessage())
			.build();
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
