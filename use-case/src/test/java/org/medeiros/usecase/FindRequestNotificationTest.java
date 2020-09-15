package org.medeiros.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medeiros.domain.Message;
import org.medeiros.usecase.exception.NotificationException;
import org.medeiros.usecase.port.MessageRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindRequestNotificationTest {

	@InjectMocks
	private FindRequestNotification findRequestNotification;
	@Mock
	private MessageRepository repository;

	@Test
	public void whenFindMessageThenReturn() throws NotificationException {
		var id = UUID.randomUUID().toString();
		when(repository.find(id)).thenReturn(Optional.of(Message.builder().id(id).build()));
		var message = findRequestNotification.find(id);
		assertThat(message).isNotNull()
			.satisfies(m -> assertThat(m.getId()).isEqualTo(id));
		verify(repository).find(id);
	}

	@Test
	public void whenHasNoMessageThenReturnNotFound() throws NotificationException {
		var id = UUID.randomUUID().toString();
		when(repository.find(id)).thenReturn(Optional.empty());
		assertThatThrownBy(() -> findRequestNotification.find(id))
			.hasMessage("Mensagem com o id: " + id + " não foi encontrada, confira se o identificador está correto e tente novamente.");
	}

}
