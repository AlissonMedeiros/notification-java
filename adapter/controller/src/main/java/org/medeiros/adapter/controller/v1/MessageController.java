package org.medeiros.adapter.controller.v1;

import org.medeiros.adapter.controller.v1.dto.MessageCreateDto;
import org.medeiros.adapter.controller.v1.dto.MessageResponseDto;
import org.medeiros.usecase.DeleteRequestNotification;
import org.medeiros.usecase.FindRequestNotification;
import org.medeiros.usecase.PushRequestNotification;
import org.medeiros.usecase.exception.NotificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/message/")
public class MessageController {

	private final FindRequestNotification findRequestNotification;
	private final PushRequestNotification pushRequestNotification;
	private final DeleteRequestNotification deleteRequestNotification;
	private final ControllerMessageMapper mapper;

	public MessageController(FindRequestNotification findRequestNotification,
							 PushRequestNotification pushRequestNotification,
							 DeleteRequestNotification deleteRequestNotification,
							 ControllerMessageMapper mapper) {
		this.findRequestNotification = findRequestNotification;
		this.pushRequestNotification = pushRequestNotification;
		this.deleteRequestNotification = deleteRequestNotification;
		this.mapper = mapper;
	}

	@PostMapping
	public ResponseEntity<MessageResponseDto> create(@Valid @RequestBody MessageCreateDto messageDto) throws NotificationException {
		var entity = mapper.toEntity(messageDto);
		var message = pushRequestNotification.push(entity);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(mapper.toDto(message));
	}

	@GetMapping("/{id}")
	public ResponseEntity<MessageResponseDto> find(@PathVariable("id") String id) throws NotificationException {
		var message = findRequestNotification.find(id);
		return ResponseEntity.ok(mapper.toDto(message));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") String id) throws NotificationException {
		deleteRequestNotification.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(
		MethodArgumentNotValidException ex
	) {
		List<String> errors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().
			forEach(error -> errors.add(error.getDefaultMessage()));
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
