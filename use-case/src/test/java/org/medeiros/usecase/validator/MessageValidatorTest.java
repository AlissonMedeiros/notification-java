package org.medeiros.usecase.validator;

import org.junit.jupiter.api.Test;
import org.medeiros.domain.CommunicationChannel;
import org.medeiros.domain.Message;
import org.medeiros.domain.Recipient;
import org.medeiros.usecase.exception.NotificationException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MessageValidatorTest {

	private MessageValidator messageValidator = new MessageValidator();

	@Test
	public void whenHasNullBodyThenReturnError() {
		assertThatThrownBy(() -> messageValidator.validate(Message.builder()
			.channel(CommunicationChannel.WHATSAPP)
			.scheduleDate(LocalDateTime.now())
			.recipient(Recipient.builder().name("João Silva").build())
			.build()))
			.hasMessage("Mensagem não pode ser enviada, por favor informe um conteúdo e tente novamente!");
	}

	@Test
	public void whenHasEmptyBodyThenReturnError() {
		assertThatThrownBy(() -> messageValidator.validate(Message.builder()
			.channel(CommunicationChannel.WHATSAPP)
			.scheduleDate(LocalDateTime.now())
			.recipient(Recipient.builder().name("João Silva").build())
			.body("")
			.build()))
			.hasMessage("Mensagem não pode ser enviada, por favor informe um conteúdo e tente novamente!");
	}

	@Test
	public void whenHasEmptyRecipientThenReturnError() {
		assertThatThrownBy(() -> messageValidator.validate(Message.builder()
			.channel(CommunicationChannel.WHATSAPP)
			.scheduleDate(LocalDateTime.now())
			.recipient(null)
			.body("Olá, tudo bem?")
			.build()))
			.hasMessage("Mensagem precisa de um destinatário, favor informar nome, telefone e e-mail!");
	}

	@Test
	public void whenHasNullRecipientThenReturnError() {
		assertThatThrownBy(() -> messageValidator.validate(Message.builder()
			.channel(CommunicationChannel.WHATSAPP)
			.scheduleDate(LocalDateTime.now())
			.recipient(Recipient.builder().name(null).build())
			.body("Olá, tudo bem?")
			.build()))
			.hasMessage("Mensagem precisa de um destinatário, favor informar nome, telefone e e-mail!");
	}

	@Test
	public void whenHasValidRecipientThenDoNothing() throws NotificationException {
		messageValidator.validate(Message.builder()
			.channel(CommunicationChannel.WHATSAPP)
			.scheduleDate(LocalDateTime.now())
			.recipient(Recipient.builder().name("Nome").build())
			.body("Olá, tudo bem?")
			.build());
	}

}
