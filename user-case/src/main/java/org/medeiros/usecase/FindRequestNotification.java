package org.medeiros.usecase;

import org.medeiros.domain.entity.Message;
import org.medeiros.usecase.exception.MessageNotFoundException;
import org.medeiros.usecase.exception.NotificationException;
import org.medeiros.usecase.port.MessageRepository;

public class FindRequestNotification {

	private final MessageRepository repository;

	public FindRequestNotification(MessageRepository repository) {
		this.repository = repository;
	}

	public Message find(String id) throws NotificationException {
		return repository.find(id).orElseThrow(() ->
			new MessageNotFoundException("Mensagem com o id: " + id + " não foi encontrada, confira se o identificador está correto e tente novamente.")
		);
	}

}
