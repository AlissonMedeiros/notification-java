package org.medeiros.adapter.controller.v1;

import org.medeiros.adapter.controller.v1.dto.MessageDto;
import org.medeiros.usecase.DeleteRequestNotification;
import org.medeiros.usecase.FindRequestNotification;
import org.medeiros.usecase.PushRequestNotification;
import org.medeiros.usecase.exception.NotificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/message/")
public class MessageController {

	private final FindRequestNotification findRequestNotification;
	private final PushRequestNotification pushRequestNotification;
	private final DeleteRequestNotification deleteRequestNotification;

	public MessageController(FindRequestNotification findRequestNotification,
							 PushRequestNotification pushRequestNotification,
							 DeleteRequestNotification deleteRequestNotification) {
		this.findRequestNotification = findRequestNotification;
		this.pushRequestNotification = pushRequestNotification;
		this.deleteRequestNotification = deleteRequestNotification;
	}

	@PostMapping
	public ResponseEntity<MessageDto> create(@RequestBody MessageDto messageDto) throws NotificationException {
		var entity = MessageMapper.toEntity(messageDto);
		var message = pushRequestNotification.push(entity);
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(MessageMapper.toDto(message));
	}

	@GetMapping("/{id}")
	public ResponseEntity<MessageDto> find(@PathVariable("id") String id) throws NotificationException {
		var message = findRequestNotification.find(id);
		return ResponseEntity.ok(MessageMapper.toDto(message));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) throws NotificationException {
		deleteRequestNotification.delete(id);
	}

}