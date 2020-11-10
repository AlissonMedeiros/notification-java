package org.medeiros.adapter.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.medeiros.adapter.repository.entity.MessageEntity;
import org.medeiros.adapter.repository.entity.MessageEntityRepository;
import org.medeiros.domain.Message;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageGatewayTest {

	@InjectMocks
	private MessageGateway messageGateway;
	@Mock
	private MessageEntityRepository repository;
	@Spy
	private MessageMapper messageMapper = new MessageMapper();

	@Test
	public void whenCreateThenCallRepository() {
		var id = "123";
		var message = Message.builder()
			.id(id)
			.chats(List.of(org.medeiros.domain.Chat.builder().build()))
			.build();
		var createdMessage = messageGateway.create(message);
		assertThat(createdMessage).isNotNull();
		assertThat(createdMessage.getId()).isEqualTo(id);
		ArgumentCaptor<MessageEntity> captor = ArgumentCaptor.forClass(MessageEntity.class);
		verify(repository).save(captor.capture());
		var messageEntity = captor.getValue();
		assertThat(messageEntity.getChats().iterator().next().getMessage().getId()).isEqualTo(id);

	}

	@Test
	public void whenDeleteThenCallRepository() {
		String id = "123";
		messageGateway.delete(id);
		verify(repository).deleteById(id);
	}

	@Test
	public void whenExistThenCallRepository() {
		String id = "123";
		when(repository.existsById(id)).thenReturn(true);
		var result = messageGateway.exists(id);
		assertThat(result).isTrue();
		verify(repository).existsById(id);
	}

	@Test
	public void whenNotExistThenCallRepository() {
		String id = "123";
		when(repository.existsById(id)).thenReturn(false);
		var result = messageGateway.exists(id);
		assertThat(result).isFalse();
		verify(repository).existsById(id);
	}

	@Test
	public void whenFindByExistentIdThenCallRepository() {
		String id = "123";
		when(repository.findById(id)).thenReturn(Optional.of(MessageEntity.builder().id(id).build()));
		var result = messageGateway.find(id);
		assertThat(result.get().getId()).isEqualTo(id);
		verify(repository).findById(id);
	}

	@Test
	public void whenFindByInvalidIdThenCallRepository() {
		String id = "123";
		when(repository.findById(id)).thenReturn(Optional.empty());
		var result = messageGateway.find(id);
		assertThat(result.isEmpty()).isTrue();
		verify(repository).findById(id);
	}

}
