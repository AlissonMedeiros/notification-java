package org.medeiros.adapter.controller.v1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medeiros.adapter.controller.v1.dto.MessageCreateDto;
import org.medeiros.adapter.controller.v1.dto.MessageResponseDto;
import org.medeiros.domain.Message;
import org.medeiros.usecase.DeleteRequestNotification;
import org.medeiros.usecase.FindRequestNotification;
import org.medeiros.usecase.PushRequestNotification;
import org.medeiros.usecase.exception.NotificationException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageControllerTest {

	@InjectMocks
	private MessageController messageController;
	@Mock
	private FindRequestNotification findRequestNotification;
	@Mock
	private PushRequestNotification pushRequestNotification;
	@Mock
	private DeleteRequestNotification deleteRequestNotification;
	@Spy
	private ControllerMessageMapper mapper = new ControllerMessageMapper();
	@Mock
	private BindingResult bindingResult;

	@Test
	public void whenDeleteTheReturn204() throws NotificationException {
		String id = "123";
		ResponseEntity response = messageController.delete(id);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		assertThat(response.getBody()).isNull();
		verify(deleteRequestNotification).delete(id);
		verify(mapper, never()).toEntity(any());
		verify(mapper, never()).toDto(any());
	}

	@Test
	public void whenFindByIdTheReturn200() throws NotificationException {
		String id = "123";
		when(findRequestNotification.find(id)).thenReturn(Message.builder()
			.id(id)
			.build());
		ResponseEntity response = messageController.find(id);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isEqualTo(MessageResponseDto.builder()
			.id(id)
			.build());
		verify(findRequestNotification).find(id);
		verify(mapper, never()).toEntity(any());
		verify(mapper).toDto(any());
	}

	@Test
	public void whenCreateTheReturn201() throws NotificationException {
		String id = "123";
		String body = "Olá";
		Message message = Message.builder()
			.body(body)
			.build();
		when(pushRequestNotification.push(message)).thenReturn(Message.builder()
			.id(id)
			.body(body)
			.build());
		ResponseEntity response = messageController.create(MessageCreateDto.builder()
			.body(body)
			.build());
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody()).isEqualTo(MessageResponseDto.builder()
			.id(id)
			.body(body)
			.build());
		verify(pushRequestNotification).push(message);
		verify(mapper).toEntity(any());
		verify(mapper).toDto(any());
	}

	@Test
	public void whenCreateTheReturn21() throws NotificationException {
		when(bindingResult.getAllErrors()).thenReturn(List.of(new FieldError("a", "b", "Error")));
		MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
		var response = messageController.handleValidationExceptions(exception);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(response.getBody()).isEqualTo(List.of("Error"));
	}

}
