package org.medeiros.usecase;

import org.medeiros.domain.Chat;
import org.medeiros.domain.Message;
import org.medeiros.domain.Status;
import org.medeiros.usecase.exception.NotificationException;
import org.medeiros.usecase.port.MessageRepository;
import org.medeiros.usecase.validator.MessageValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PushRequestNotification {

	private final MessageRepository repository;
	private final MessageValidator messageValidator;

	public PushRequestNotification(MessageRepository repository,
								   MessageValidator messageValidator) {
		this.repository = repository;
		this.messageValidator = messageValidator;
	}

	public Message push(Message message) throws NotificationException {
		messageValidator.validate(message);
		message.setId(UUID.randomUUID().toString());
		message.setChats(List.of(Chat.builder()
			.id(UUID.randomUUID().toString())
			.date(LocalDateTime.now())
			.status(Status.WAITING)
			.build()));
		return repository.create(message);
	}

}
