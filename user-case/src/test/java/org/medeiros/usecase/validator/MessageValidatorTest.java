package org.medeiros.usecase.validator;

import org.junit.jupiter.api.Test;
import org.medeiros.domain.CommunicationChannel;
import org.medeiros.domain.Message;
import org.medeiros.domain.Recipient;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MessageValidatorTest {

	@Test
	public void whenHasNullBodyThenReturnError() {
		assertThatThrownBy(() -> MessageValidator.validate(Message.builder()
			.channel(CommunicationChannel.WHATSAPP)
			.scheduleDate(LocalDateTime.now())
			.recipient(Recipient.builder().name("João Silva").build())
			.build()))
			.hasMessage("Mensagem não pode ser enviada, por favor informe um conteúdo e tente novamente!");
	}

	@Test
	public void whenHasEmptyBodyThenReturnError() {
		assertThatThrownBy(() -> MessageValidator.validate(Message.builder()
			.channel(CommunicationChannel.WHATSAPP)
			.scheduleDate(LocalDateTime.now())
			.recipient(Recipient.builder().name("João Silva").build())
			.body("")
			.build()))
			.hasMessage("Mensagem não pode ser enviada, por favor informe um conteúdo e tente novamente!");
	}

	@Test
	public void whenHasEmptyRecipientThenReturnError() {
		assertThatThrownBy(() -> MessageValidator.validate(Message.builder()
			.channel(CommunicationChannel.WHATSAPP)
			.scheduleDate(LocalDateTime.now())
			.recipient(null)
			.body("Olá, tudo bem?")
			.build()))
			.hasMessage("Mensagem precisa de um destinatário, favor informar nome, telefone e e-mail!");
	}

	@Test
	public void whenHasNullRecipientThenReturnError() {
		assertThatThrownBy(() -> MessageValidator.validate(Message.builder()
			.channel(CommunicationChannel.WHATSAPP)
			.scheduleDate(LocalDateTime.now())
			.recipient(Recipient.builder().name(null).build())
			.body("Olá, tudo bem?")
			.build()))
			.hasMessage("Mensagem precisa de um destinatário, favor informar nome, telefone e e-mail!");
	}
}
