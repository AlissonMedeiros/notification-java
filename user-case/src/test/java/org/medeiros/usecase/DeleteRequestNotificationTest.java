package org.medeiros.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medeiros.usecase.exception.NotificationException;
import org.medeiros.usecase.port.MessageRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteRequestNotificationTest {

	@InjectMocks
	private DeleteRequestNotification deleteRequestNotification;
	@Mock
	private MessageRepository repository;


	@Test
	public void whenFindMessageThenReturn() throws NotificationException {
		var id = UUID.randomUUID().toString();
		when(repository.exists(id)).thenReturn(true);
		deleteRequestNotification.delete(id);
		verify(repository).exists(id);
		verify(repository).delete(id);
	}

	@Test
	public void whenHasNoMessageThenReturnNotFound() throws NotificationException {
		var id = UUID.randomUUID().toString();
		when(repository.exists(id)).thenReturn(false);
		assertThatThrownBy(() -> deleteRequestNotification.delete(id))
			.hasMessage("Mensagem com o id: " + id + " não existe ou já foi removida, confira se o identificador está correto.");
		verify(repository).exists(id);
		verify(repository, never()).delete(id);
		Mockito.verifyNoMoreInteractions(repository);
	}

}
