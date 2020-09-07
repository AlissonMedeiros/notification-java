package org.medeiros.usecase;

import org.medeiros.domain.entity.Message;
import org.medeiros.usecase.exception.NotificationException;
import org.medeiros.usecase.port.MessageRepository;
import org.medeiros.usecase.validator.MessageValidator;

import java.util.UUID;

public class PushRequestNotification {

	private final MessageRepository repository;

	public PushRequestNotification(MessageRepository repository) {
		this.repository = repository;
	}

	public Message push(Message message) throws NotificationException {
		MessageValidator.validate(message);
		message.setId(UUID.randomUUID().toString());
		return repository.create(message);
	}

}
