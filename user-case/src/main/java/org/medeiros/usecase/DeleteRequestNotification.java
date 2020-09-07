package org.medeiros.usecase;

import org.medeiros.usecase.exception.MessageNotFoundException;
import org.medeiros.usecase.exception.NotificationException;
import org.medeiros.usecase.port.MessageRepository;

public class DeleteRequestNotification {

	private final MessageRepository repository;

	public DeleteRequestNotification(MessageRepository repository) {
		this.repository = repository;
	}

	public void delete(String id) throws NotificationException {
		if (repository.exists(id))
			repository.delete(id);
		else
			throw new MessageNotFoundException("Mensagem com o id: " + id + " não existe ou já foi removida, confira se o identificador está correto.");
	}

}
