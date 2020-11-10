package org.medeiros.adapter.controller.v1.handler;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.medeiros.usecase.exception.MessageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ApplicationExceptionHandlerTest {

	private ApplicationExceptionHandler applicationExceptionHandler = new ApplicationExceptionHandler();

	@Test
	public void whenHasMessageNotFoundExceptionTheReturn404() {
		ResponseEntity entity = applicationExceptionHandler.handleMessageNotFoundException(new MessageNotFoundException("Error"), null);
		Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		Assertions.assertThat(entity.getBody()).isEqualTo(ErrorDto.builder().message("Error").build());
	}

}
