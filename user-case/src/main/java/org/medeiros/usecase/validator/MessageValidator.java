package org.medeiros.usecase.validator;

import org.medeiros.domain.Message;
import org.medeiros.domain.Recipient;
import org.medeiros.usecase.exception.InvalidMessageException;
import org.medeiros.usecase.exception.InvalidRecipientException;
import org.medeiros.usecase.exception.NotificationException;

public class MessageValidator {

	private MessageValidator() {

	}

	public static void validate(Message message) throws NotificationException {
		if (message.getBody() == null || message.getBody().isBlank())
			throw new InvalidMessageException("Mensagem não pode ser enviada, por favor informe um conteúdo e tente novamente!");
		Recipient recipient = message.getRecipient();
		if (recipient == null || recipient.getName() == null)
			throw new InvalidRecipientException("Mensagem precisa de um destinatário, favor informar nome, telefone e e-mail!");

	}

}

